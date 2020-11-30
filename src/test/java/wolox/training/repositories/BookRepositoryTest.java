package wolox.training.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;
import javax.persistence.PersistenceException;
import org.hibernate.PropertyValueException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import wolox.training.commons.BookFactory;
import wolox.training.models.Book;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void givenBook_whenSave_returnOk() {
        entityManager.persistAndFlush(BookFactory.getBook());
        Optional<Book> book = bookRepository.findById(1l);
        assertThat(book).isNotEmpty();
    }


    @Test
    public void givenBook_whenSave_returnPersistenceException() {
        Exception exception = assertThrows(PersistenceException.class, () ->
                entityManager.merge(new Book(1L, "Terror", null, "image2.pgn", "It", "-",
                        "Viking Press", "1986", 200, "45788865", null)));
        assertTrue(exception.getCause() instanceof PropertyValueException);
    }

    @Test
    public void givenBook_whenIsNotNull_returnOk() {
        assertThat(BookFactory.getBook().getAuthor()).isNotNull();
        assertThat(BookFactory.getBook().getImage()).isNotNull();
        assertThat(BookFactory.getBook().getTitle()).isNotNull();
        assertThat(BookFactory.getBook().getSubtitle()).isNotNull();
        assertThat(BookFactory.getBook().getYear()).isNotNull();
        assertThat(BookFactory.getBook().getPages()).isNotNull();
        assertThat(BookFactory.getBook().getIsbn()).isNotNull();
    }

    @Test
    public void givenBook_whenIsNull_returnException() {
        entityManager.persistAndFlush(BookFactory.getBook());
        entityManager.persistAndFlush(BookFactory.getBook());
        entityManager.remove(BookFactory.getBook());
        List<Book> book = bookRepository.findAll();
        assertThat(book).isEmpty();
    }
}
