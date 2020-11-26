package wolox.training.repositories;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
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
import wolox.training.models.User;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;
    private User mockUser;
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

        mockUser = new User();
        mockUser.setUserName("SsopoWolox");
        mockUser.setName("Sebastian Sopo Martinez");
        mockUser.setBirthdate(LocalDate.now());
        Book book = new Book(1L, "Terror", "Stephen King", "image2.pgn", "It", "-", "Viking Press",
                "1986", 220, "45788865");
        List books = new ArrayList();
        books.add(book);
        mockUser.setBooks(books);
    }

    @Test
    public void givenUser_whenSave_returnOk() {
        entityManager.persistAndFlush(mockUser);
        Optional<User> user = userRepository.findById(1l);
        org.assertj.core.api.Assertions.assertThat(user).isNotEmpty();
    }


    @Test
    public void givenUser_whenSave_returnPersistenceException() {
        Exception exception = Assertions.assertThrows(PersistenceException.class, () ->
                entityManager.merge(new User(1L, null, "Sebastian Sopo Martinez", LocalDate.now(),
                        Arrays.asList(mockBook))));
        Assertions.assertTrue(exception.getCause() instanceof PropertyValueException);
    }

    @Test
    public void givenBook_whenIsNotNull_returnOk() {
        org.assertj.core.api.Assertions.assertThat(mockUser.getName()).isNotNull();
        org.assertj.core.api.Assertions.assertThat(mockUser.getUserName()).isNotNull();
        org.assertj.core.api.Assertions.assertThat(mockUser.getBirthdate()).isNotNull();
        org.assertj.core.api.Assertions.assertThat(mockUser.getBooks()).isNotNull();
        org.assertj.core.api.Assertions.assertThat(mockUser.getBooks()).isNotEmpty();
    }

    @Test
    public void givenBook_whenIsNull_returnException() {
        entityManager.persistAndFlush(mockBook);
        entityManager.persistAndFlush(mockUser);
        entityManager.remove(mockUser);
        List<User> userList = userRepository.findAll();
        org.assertj.core.api.Assertions.assertThat(userList).isEmpty();
    }
}
