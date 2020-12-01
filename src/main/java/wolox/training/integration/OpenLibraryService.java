package wolox.training.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import wolox.training.commons.Constants;
import wolox.training.dto.BookDTO;
import wolox.training.integration.dto.Book;
import wolox.training.mapper.BookMapper;

@Component
public class OpenLibraryService {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private Environment env;

    public BookDTO bookInfo(String isbn) throws ResponseStatusException {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> vars = new HashMap<>();
        vars.put("isbn", isbn);
        vars.put("format", Constants.FORMAT);
        vars.put("jscmd", Constants.TYPE);

        ObjectNode objectNode = (ObjectNode) restTemplate
                .getForObject(env.getProperty(Constants.URL), ObjectNode.class, vars)
                .get(Constants.ISBN.concat(isbn));
        if (objectNode != null) {
            BookDTO bookDTO = bookMapper
                    .bookToBookDTO(objectMapper.convertValue(objectNode, Book.class));
            bookDTO.setIsbn(isbn);
            return bookDTO;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    Constants.NOT_FOUND);
        }
    }
}
