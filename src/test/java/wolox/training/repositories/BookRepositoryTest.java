package wolox.training.repositories;

import java.util.List;
import java.util.Optional;
import javax.persistence.PersistenceException;
import org.hibernate.PropertyValueException;
import org.junit.jupiter.api.Assertions;
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
        org.assertj.core.api.Assertions.assertThat(book).isNotEmpty();
    }


    @Test
    public void givenBook_whenSave_returnPersistenceException() {
        Exception exception = Assertions.assertThrows(PersistenceException.class, () ->
                entityManager.merge(new Book(1L, "Terror", null, "image2.pgn", "It", "-",
                        "Viking Press", "1986", 200, "45788865", null)));
        Assertions.assertTrue(exception.getCause() instanceof PropertyValueException);
    }

    @Test
    public void givenBook_whenIsNotNull_returnOk() {
        org.assertj.core.api.Assertions.assertThat(BookFactory.getBook().getAuthor()).isNotNull();
        org.assertj.core.api.Assertions.assertThat(BookFactory.getBook().getImage()).isNotNull();
        org.assertj.core.api.Assertions.assertThat(BookFactory.getBook().getTitle()).isNotNull();
        org.assertj.core.api.Assertions.assertThat(BookFactory.getBook().getSubtitle()).isNotNull();
        org.assertj.core.api.Assertions.assertThat(BookFactory.getBook().getYear()).isNotNull();
        org.assertj.core.api.Assertions.assertThat(BookFactory.getBook().getPages()).isNotNull();
        org.assertj.core.api.Assertions.assertThat(BookFactory.getBook().getIsbn()).isNotNull();
    }

    @Test
    public void givenBook_whenIsNull_returnException() {
        entityManager.persistAndFlush(BookFactory.getBook());
        entityManager.persistAndFlush(BookFactory.getBook());
        entityManager.remove(BookFactory.getBook());
        List<Book> book = bookRepository.findAll();
        org.assertj.core.api.Assertions.assertThat(book).isEmpty();
    }
}
