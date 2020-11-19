package Wolox.training.repositories;

import Wolox.training.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE LOWER(b.author) = LOWER(:author)")
    Book getBook(@Param("author") String author);

}
