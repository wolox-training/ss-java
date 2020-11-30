package wolox.training.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import wolox.training.commons.BookFactory;
import wolox.training.commons.Constants;
import wolox.training.commons.Utils;
import wolox.training.provider.CustomAuthenticationProvider;
import wolox.training.repositories.BookRepository;

@WebMvcTest(BookController.class)
public class BookControllerTest extends Utils {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookRepository mockBookRepository;

    @MockBean
    private CustomAuthenticationProvider authProvider;

    @Test
    @WithMockUser(username = Constants.USER_NAME, password = Constants.PASSWORD)
    public void givenBook_whenFindOne_thenReturnBook() throws Exception {
        when(mockBookRepository.findById(1L))
                .thenReturn(java.util.Optional.ofNullable(BookFactory.getBook()));
        mvc.perform(MockMvcRequestBuilders.get("/api/books/{id}", 1)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("title")
                        .value(BookFactory.getBook().getTitle()));
    }

    @Test
    @WithMockUser(username = Constants.USER_NAME, password = Constants.PASSWORD)
    public void givenBook_whenFindAll_thenReturnBooks() throws Exception {
        when(mockBookRepository.findAll())
                .thenReturn(Collections.singletonList(BookFactory.getBook()));
        mvc.perform(MockMvcRequestBuilders.get("/api/books/all")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("[0].author")
                        .value(BookFactory.getBook().getAuthor()))
                .andExpect(MockMvcResultMatchers.jsonPath("[0].genre")
                        .value(BookFactory.getBook().getGenre()))
                .andExpect(MockMvcResultMatchers.jsonPath("[0].title")
                        .value(BookFactory.getBook().getTitle()));
    }

    @Test
    @WithMockUser(username = Constants.USER_NAME, password = Constants.PASSWORD)
    public void givenBook_whenSave_thenReturnBook() throws Exception {
        when(mockBookRepository.save(any())).thenReturn(BookFactory.getBook());
        mvc.perform(MockMvcRequestBuilders.post("/api/books")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(asJsonString(BookFactory.getBook()))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("author")
                        .value(BookFactory.getBook().getAuthor()))
                .andExpect(MockMvcResultMatchers.jsonPath("genre")
                        .value(BookFactory.getBook().getGenre()))
                .andExpect(MockMvcResultMatchers.jsonPath("title")
                        .value(BookFactory.getBook().getTitle()));
    }

    @Test
    @WithMockUser(username = Constants.USER_NAME, password = Constants.PASSWORD)
    public void givenBook_whenDelete_thenReturnOk() throws Exception {
        when(mockBookRepository.findById(1L))
                .thenReturn(java.util.Optional.ofNullable(BookFactory.getBook()));
        doNothing().when(mockBookRepository).delete(BookFactory.getBook());
        mvc.perform(MockMvcRequestBuilders.delete("/api/books/{id}", 1)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = Constants.USER_NAME, password = Constants.PASSWORD)
    public void givenBook_whenUpdate_thenReturnUpdateBook() throws Exception {
        when(mockBookRepository.findById(1L))
                .thenReturn(java.util.Optional.ofNullable(BookFactory.getBook()));
        when(mockBookRepository.save(any())).thenReturn(BookFactory.getBook());
        mvc.perform(MockMvcRequestBuilders.put("/api/books/{id}", 1)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(Constants.BOOKS)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("author")
                        .value(BookFactory.getBook().getAuthor()))
                .andExpect(MockMvcResultMatchers.jsonPath("genre")
                        .value(BookFactory.getBook().getGenre()))
                .andExpect(MockMvcResultMatchers.jsonPath("title")
                        .value(BookFactory.getBook().getTitle()));

    }
}
