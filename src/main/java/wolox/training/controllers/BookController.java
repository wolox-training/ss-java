package wolox.training.controllers;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import wolox.training.commons.Constants;
import wolox.training.dto.BookDTO;
import wolox.training.integration.OpenLibraryService;
import wolox.training.mapper.BookMapper;
import wolox.training.models.Book;
import wolox.training.repositories.BookRepository;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private OpenLibraryService openService;

    @Autowired
    private BookMapper bookMapper;

    /**
     * this method create a book
     *
     * @param book
     * @return
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    /**
     * This method delete a book by id
     *
     * @param id
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                Constants.NOT_FOUND));
        bookRepository.deleteById(id);
    }

    /**
     * This method update book information by id
     *
     * @param book
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    public Book updateBook(@RequestBody Book book, @PathVariable Long id) {
        if (book.getId() != id) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Constants.INCORRECT);
        }
        bookRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                Constants.NOT_FOUND));
        return bookRepository.save(book);
    }

    /**
     * This method return all books
     *
     * @return
     */
    @GetMapping("/all")
    public Iterable findAll() {
        return bookRepository.findAll();
    }

    /**
     * This method return a book by id
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Book findOne(@PathVariable Long id) {
        return bookRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                Constants.NOT_FOUND));
    }

    /**
     * This method find a Book by ISBN, if not return find in a external service and persist in data
     * base
     *
     * @param isbn
     * @return
     */
    @GetMapping()
    public ResponseEntity findByIsbn(@RequestParam("isbn") String isbn) {
        Optional<Book> book = bookRepository.findByIsbn(isbn);
        if (!book.isPresent()) {
            BookDTO bookDTO = openService.bookInfo(isbn);
            Book bookEntity = bookMapper.bookDTOToBookEntity(bookDTO);
            bookEntity.setAuthor(bookDTO.getAuthors().get(0).getName());
            bookEntity.setPublisher(bookDTO.getPublishers().get(0).getName());
            bookEntity.setGenre("Terror");
            bookEntity.setImage("image5.pgn");
            return new ResponseEntity(bookRepository.save(bookEntity), HttpStatus.CREATED);
        } else {
            return new ResponseEntity(book, HttpStatus.OK);
        }
    }
}
