package wolox.training.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
import wolox.training.commons.Constants;
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

    @BeforeEach
    public void setUp() {
        mockUser = new User();
        mockUser.setUserName("SsopoWolox");
        mockUser.setName("Sebastian Sopo Martinez");
        mockUser.setBirthdate(LocalDate.now());
        Book book = new Book(1L, "Terror", "Stephen King", "image2.pgn", "It", "-", "Viking Press",
                "1986", 220, "45788865", null);
        List books = new ArrayList();
        books.add(book);
        mockUser.setBooks(books);
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
                .content(Constants.USER_ID_NULL)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("userName")
                        .value(mockUser.getUserName()))
                .andExpect(MockMvcResultMatchers.jsonPath("name").value(mockUser.getName()));
    }

    @Test
    public void givenUser_whenDelete_thenReturnOk() throws Exception {
        when(mockUserRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(mockUser));
        doNothing().when(mockUserRepository).delete(mockUser);
        mvc.perform(MockMvcRequestBuilders.delete("/api/users/{id}", 1)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void givenUser_whenUpdate_thenReturnUpdateUser() throws Exception {
        when(mockUserRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(mockUser));
        when(mockUserRepository.save(any())).thenReturn(mockUser);
        mvc.perform(MockMvcRequestBuilders.put("/api/users/{id}", 1)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(Constants.USER)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("userName")
                        .value(mockUser.getUserName()))
                .andExpect(MockMvcResultMatchers.jsonPath("name").value(mockUser.getName()));

    }

    @Test
    public void givenUser_whenSaveUserBook_thenReturnOk() throws Exception {
        when(mockBookRepository.findById(2L)).thenReturn(java.util.Optional.ofNullable(
                new Book(2L, "Terror", "Stephen King", "image2.pgn", "It", "-", "Viking Press",
                        "1986", 220, "45788865", null)));
        when(mockUserRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(mockUser));
        when(mockUserRepository.save(any())).thenReturn(mockUser);
        mvc.perform(MockMvcRequestBuilders.post("/api/users/{idUser}/book/{idBook}", 1, 2)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void givenUser_whenRemoveUserBook_thenReturnOk() throws Exception {
        when(mockBookRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(
                new Book(1L, "Terror", "Stephen King", "image2.pgn", "It", "-", "Viking Press",
                        "1986", 220, "45788865", null)));
        when(mockUserRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(mockUser));
        when(mockUserRepository.save(any())).thenReturn(mockUser);
        mvc.perform(MockMvcRequestBuilders.delete("/api/users/{idUser}/book/{idBook}", 1, 1)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
