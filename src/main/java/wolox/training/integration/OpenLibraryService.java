package wolox.training.integration;

import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import wolox.training.commons.Constants;
import wolox.training.dto.BookDTO;
import wolox.training.mapper.BookMapper;

@Component
public class OpenLibraryService {

    @Autowired
    private BookMapper bookMapper;

    public BookDTO bookInfo(String isbn) throws ResponseStatusException {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> vars = new HashMap<>();
        vars.put("isbn", isbn);
        vars.put("format", Constants.FORMAT);
        vars.put("jscmd", Constants.TYPE);

        ObjectNode objectNode = restTemplate.getForObject(Constants.URL, ObjectNode.class, vars);
        objectNode = (ObjectNode) objectNode.get(Constants.ISBN.concat(isbn));
        if (objectNode != null) {
            return null;
            //return bookMapper.bookToBookDTO(bookMapper.toBook(objectNode));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    Constants.NOT_FOUND);
        }
    }
}
