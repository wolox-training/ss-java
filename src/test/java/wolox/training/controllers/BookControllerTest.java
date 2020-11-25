package wolox.training.controllers;

import wolox.training.models.Book;
import wolox.training.repositories.BookRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookRepository mockBookRepository;
    private Book mockBook;

    @BeforeAll
    public static void setUp() {
        Book book = new Book();
        book.setAuthor("Stephen King");
        book.setGenre("Terror");
        book.setImage("image2.pgn");
        book.setIsbn("45788865");
        book.setPages(200);
        book.setPublisher("Viking Press");
        book.setSubtitle("-");
        book.setTitle("It");
        book.setYear("1986");
    }

    @Test
    public void givenBook_whenFinOne_thenReturnBookJson() throws Exception {
        Mockito.when(mockBookRepository.findById(1L)).thenReturn(
                java.util.Optional.ofNullable(mockBook));
        mvc.perform(MockMvcRequestBuilders.get("/api/books/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.book.title").value("It"));
    }
}
