package wolox.training.repositories;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.persistence.PersistenceException;
import org.hibernate.PropertyValueException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import wolox.training.models.Book;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BookRepository bookRepository;
    private Book mockBook;

    @BeforeEach
    public void setUp() {
        mockBook = new Book();
        mockBook.setAuthor("Stephen King");
        mockBook.setGenre("Terror");
        mockBook.setImage("image2.pgn");
        mockBook.setIsbn("45788865");
        mockBook.setPages(200);
        mockBook.setPublisher("Viking Press");
        mockBook.setSubtitle("-");
        mockBook.setTitle("It");
        mockBook.setYear("1986");
        mockBook.setUsers(Collections.EMPTY_LIST);
    }

    @Test
    public void givenBook_whenSave_returnOk() {
        entityManager.persistAndFlush(mockBook);
        Optional<Book> book = bookRepository.findById(1l);
        org.assertj.core.api.Assertions.assertThat(book).isNotEmpty();
    }


    @Test
    public void givenBook_whenSave_returnPersistenceException() {
        Exception exception = Assertions.assertThrows(PersistenceException.class, () ->
                entityManager.merge(new Book(1L, "Terror", null, "image2.pgn", "It", "-",
                        "Viking Press", "1986", 200, "45788865")));
        Assertions.assertTrue(exception.getCause() instanceof PropertyValueException);
    }

    @Test
    public void givenBook_whenIsNotNull_returnOk() {
        org.assertj.core.api.Assertions.assertThat(mockBook.getAuthor()).isNotNull();
        org.assertj.core.api.Assertions.assertThat(mockBook.getImage()).isNotNull();
        org.assertj.core.api.Assertions.assertThat(mockBook.getTitle()).isNotNull();
        org.assertj.core.api.Assertions.assertThat(mockBook.getSubtitle()).isNotNull();
        org.assertj.core.api.Assertions.assertThat(mockBook.getYear()).isNotNull();
        org.assertj.core.api.Assertions.assertThat(mockBook.getPages()).isNotNull();
        org.assertj.core.api.Assertions.assertThat(mockBook.getIsbn()).isNotNull();
    }

    @Test
    public void givenBook_whenIsNull_returnException() {
        entityManager.persistAndFlush(mockBook);
        entityManager.persistAndFlush(mockBook);
        entityManager.remove(mockBook);
        List<Book> book = bookRepository.findAll();
        org.assertj.core.api.Assertions.assertThat(book).isEmpty();
    }
}
