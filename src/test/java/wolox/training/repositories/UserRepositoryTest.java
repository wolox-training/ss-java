package wolox.training.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.persistence.PersistenceException;
import org.hibernate.PropertyValueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import wolox.training.commons.BookFactory;
import wolox.training.models.Book;
import wolox.training.models.User;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;
    private User mockUser;

    @BeforeEach
    public void setUp() {
        mockUser = new User();
        mockUser.setUserName("SsopoWolox");
        mockUser.setName("Sebastian Sopo Martinez");
        mockUser.setBirthdate(LocalDate.now());
        mockUser.setPassword("wolox1189");
        Book book = new Book(1L, "Terror", "Stephen King", "image2.pgn", "It", "-", "Viking Press",
                "1986", 220, "45788865", null);
        List books = new ArrayList();
        books.add(book);
        mockUser.setBooks(books);
    }

    @Test
    public void givenUser_whenSave_returnOk() {
        entityManager.persistAndFlush(mockUser);
        Optional<User> user = userRepository.findById(1l);
        assertThat(user).isNotEmpty();
    }


    @Test
    public void givenUser_whenSave_returnPersistenceException() {
        Exception exception = assertThrows(PersistenceException.class, () ->
                entityManager.merge(new User(1L, null, "Sebastian Sopo Martinez", LocalDate.now(),
                        Arrays.asList(BookFactory.getBook()))));
        assertTrue(exception.getCause() instanceof PropertyValueException);
    }

    @Test
    public void givenBook_whenIsNotNull_returnOk() {
        assertThat(mockUser.getName()).isNotNull();
        assertThat(mockUser.getUserName()).isNotNull();
        assertThat(mockUser.getBirthdate()).isNotNull();
        assertThat(mockUser.getBooks()).isNotNull();
        assertThat(mockUser.getBooks()).isNotEmpty();
    }

    @Test
    public void givenBook_whenIsNull_returnException() {
        entityManager.persistAndFlush(BookFactory.getBook());
        entityManager.persistAndFlush(mockUser);
        entityManager.remove(mockUser);
        List<User> userList = userRepository.findAll();
        assertThat(userList).isEmpty();
    }
}
