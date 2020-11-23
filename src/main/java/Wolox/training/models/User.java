package Wolox.training.models;

import Wolox.training.commons.Constants;
import Wolox.training.exceptions.BookAlreadyOwnedException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;

@Entity
@Table(name = "users")
public class User {

    public User() {
    }

    /**
     * Primary key of User entity
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotNull
    @Column(nullable = false)
    private String userName;
    @NotNull
    @Column(nullable = false)
    private String name;
    @NotNull
    @Column(nullable = false)
    private LocalDate birthdate;
    @NotNull
    @Column(nullable = false)
    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Book> books = new ArrayList<Book>();

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public List<Book> getBooks() {
        return (List<Book>) Collections.unmodifiableList(books);
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Long getId() {
        return id;
    }

    /**
     * This method save a book in the collection user if this not already exists
     *
     * @param book
     * @throws BookAlreadyOwnedException
     */
    public void addBook(Book book) throws BookAlreadyOwnedException {
        if (!books.isEmpty()) {
            if (books.stream().anyMatch(item -> item.getId().compareTo(book.getId()) == 0)) {
                books.add(book);
            } else {
                throw new BookAlreadyOwnedException(HttpStatus.PRECONDITION_FAILED,
                        Constants.ALREADY_EXISTS);
            }
        } else {
            throw new BookAlreadyOwnedException(HttpStatus.NOT_FOUND, Constants.NOT_FOUND);
        }
    }

    /**
     * This method remove a book the collection user
     *
     * @param book
     * @throws BookAlreadyOwnedException
     */
    public void removeBook(Book book) throws BookAlreadyOwnedException {
        if (books.stream().anyMatch(item -> item.getId().compareTo(book.getId()) != 0)) {
            books.remove(book);
        } else {
            throw new BookAlreadyOwnedException(HttpStatus.NOT_FOUND, Constants.NOT_FOUND);
        }
    }
}
