package Wolox.training.repositories;

import Wolox.training.models.Book;
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
}
