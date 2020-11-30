package wolox.training.repositories;

import org.springframework.data.jpa.repository.Query;
import wolox.training.models.Book;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

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
    @Query("SELECT b FROM Book b WHERE b.isbn = ?1")
    Optional<Book> findByIsbn(String isbn);
}
