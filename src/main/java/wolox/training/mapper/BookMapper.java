package wolox.training.mapper;

import com.fasterxml.jackson.databind.node.ObjectNode;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import wolox.training.dto.BookDTO;
import wolox.training.integration.dto.Book;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface BookMapper {

    Book toBook(ObjectNode objectNode);

    BookDTO bookToBookDTO(Book book);
}
