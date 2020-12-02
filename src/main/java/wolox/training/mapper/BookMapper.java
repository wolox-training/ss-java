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
            @Mapping(target = "pages", source = "book.numberOfPages"),
    })
    BookDTO bookToBookDTO(Book book);

    @Mappings({
            @Mapping(target = "year", source = "dto.publishDate"),
    })
    wolox.training.models.Book bookDTOToBookEntity(BookDTO dto);
}
