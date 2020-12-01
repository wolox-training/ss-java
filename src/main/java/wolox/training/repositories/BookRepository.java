package wolox.training.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import wolox.training.models.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

    /**
     * This method get a book by author
     *
     * @param author
     * @return
     */
    Optional<Book> findByAuthor(String author);

    /**
     * This method get a book by isbn
     *
     * @param isbn
     * @return
     */
    Optional<Book> findByIsbn(String isbn);

    /**
     * This method get a book by filter the Genre, Year and Publisher
     *
     * @param genre
     * @param year
     * @param publisher
     * @return
     */
    List<Book> findByGenreAndYearAndPublisher(String genre, String year, String publisher);
}
