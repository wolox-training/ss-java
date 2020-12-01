package wolox.training.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import wolox.training.dto.BookDTO;
import wolox.training.integration.dto.Book;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface BookMapper {

    @Mappings({
            @Mapping(target = "title", source = "book.title"),
            @Mapping(target = "subtitle", source = "book.subtitle"),
            @Mapping(target = "publishDate", source = "book.publishDate"),
            @Mapping(target = "pages", source = "book.numberOfPages"),
            @Mapping(target = "publishers", source = "book.publishers"),
            @Mapping(target = "authors", source = "book.authors")
    })
    BookDTO bookToBookDTO(Book book);

    @Mappings({
            @Mapping(target = "title", source = "dto.title"),
            @Mapping(target = "subtitle", source = "dto.subtitle"),
            @Mapping(target = "year", source = "dto.publishDate"),
            @Mapping(target = "pages", source = "dto.pages"),
            @Mapping(target = "isbn", source = "dto.isbn")
    })
    wolox.training.models.Book bookDTOToBookEntity(BookDTO dto);
}
