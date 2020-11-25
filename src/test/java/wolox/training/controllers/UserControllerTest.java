package wolox.training.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import wolox.training.commons.Utils;
import wolox.training.models.Book;
import wolox.training.models.User;
import wolox.training.repositories.BookRepository;
import wolox.training.repositories.UserRepository;

@WebMvcTest(UserController.class)
public class UserControllerTest extends Utils {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookRepository mockBookRepository;

    @MockBean
    private UserRepository mockUserRepository;

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
        mockUser.setBooks(Collections.singletonList(mockBook));
    }

    @Test
    public void givenUser_whenFindOne_thenReturnUser() throws Exception {
        when(mockUserRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(mockUser));
        mvc.perform(MockMvcRequestBuilders.get("/api/users/{id}", 1)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(
                        MockMvcResultMatchers.jsonPath("userName").value(mockUser.getUserName()));
    }

    @Test
    public void givenUser_whenFindAll_thenReturnUsers() throws Exception {
        when(mockUserRepository.findAll()).thenReturn(Collections.singletonList(mockUser));
        mvc.perform(MockMvcRequestBuilders.get("/api/users")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("[0].userName")
                        .value(mockUser.getUserName()))
                .andExpect(MockMvcResultMatchers.jsonPath("[0].name").value(mockUser.getName()));
    }

    @Test
    public void givenUser_whenSave_thenReturnUser() throws Exception {
        when(mockUserRepository.save(any())).thenReturn(mockUser);
        mvc.perform(MockMvcRequestBuilders.post("/api/users")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(
                        "{\"id\":null,\"userName\":\"SsopoWolox\",\"name\":\"Sebastian Sopo Martinez\",\"birthdate\":2020}")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("userName")
                        .value(mockUser.getUserName()))
                .andExpect(MockMvcResultMatchers.jsonPath("name").value(mockUser.getName()));
    }
}
