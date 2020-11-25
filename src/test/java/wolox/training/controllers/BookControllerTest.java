package wolox.training.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

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
import wolox.training.repositories.BookRepository;

@WebMvcTest(BookController.class)
public class BookControllerTest extends Utils {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookRepository mockBookRepository;
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
    public void givenBook_whenFindOne_thenReturnBook() throws Exception {
        when(mockBookRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(mockBook));
        mvc.perform(MockMvcRequestBuilders.get("/api/books/{id}", 1)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("title").value(mockBook.getTitle()));
    }

    @Test
    public void givenBook_whenFindAll_thenReturnBooks() throws Exception {
        when(mockBookRepository.findAll()).thenReturn(Collections.singletonList(mockBook));
        mvc.perform(MockMvcRequestBuilders.get("/api/books")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("[0].author").value(mockBook.getAuthor()))
                .andExpect(MockMvcResultMatchers.jsonPath("[0].genre").value(mockBook.getGenre()))
                .andExpect(MockMvcResultMatchers.jsonPath("[0].title").value(mockBook.getTitle()));
    }

    @Test
    public void givenBook_whenSave_thenReturnBook() throws Exception {
        when(mockBookRepository.save(any())).thenReturn(mockBook);
        mvc.perform(MockMvcRequestBuilders.post("/api/books")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(asJsonString(mockBook))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("author").value(mockBook.getAuthor()))
                .andExpect(MockMvcResultMatchers.jsonPath("genre").value(mockBook.getGenre()))
                .andExpect(MockMvcResultMatchers.jsonPath("title").value(mockBook.getTitle()));
    }

    @Test
    public void givenBook_whenDelete_thenReturnOk() throws Exception {
        when(mockBookRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(mockBook));
        doNothing().when(mockBookRepository).delete(mockBook);
        mvc.perform(MockMvcRequestBuilders.delete("/api/books/{id}", 1)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void givenBook_whenUpdate_thenReturnUpdateBook() throws Exception {
        when(mockBookRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(mockBook));
        when(mockBookRepository.save(any())).thenReturn(mockBook);
        mvc.perform(MockMvcRequestBuilders.put("/api/books/{id}", 1)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(
                        "{\"id\":1,\"genre\":\"Terror\",\"author\":\"Stephen King\",\"image\":\"image2.pgn\",\"title\":\"It\",\"subtitle\":\"-\",\"publisher\":\"Viking Press\",\"year\":\"1986\",\"pages\":200,\"isbn\":\"45788865\"}")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("author").value(mockBook.getAuthor()))
                .andExpect(MockMvcResultMatchers.jsonPath("genre").value(mockBook.getGenre()))
                .andExpect(MockMvcResultMatchers.jsonPath("title").value(mockBook.getTitle()));

    }
}
