package wolox.training.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import wolox.training.commons.Constants;
import wolox.training.exceptions.BookAlreadyOwnedException;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;

@Entity
@Table(name = "users")
@ApiModel(description = "Entity Class User from training")
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
    @ApiModelProperty(notes = "Parameter name the user")
    private String name;
    @NotNull
    @Column(nullable = false)
    private LocalDate birthdate;
    @NotNull
    @Column(nullable = false)
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Book> books = new ArrayList<Book>();

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(userName));
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(name));
        this.name = name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        Preconditions.checkNotNull(birthdate);
        this.birthdate = birthdate;
    }

    public List<Book> getBooks() {
        return (List<Book>) Collections.unmodifiableList(books);
    }

    public void setBooks(List<Book> books) {
        Preconditions.checkNotNull(books);
        Preconditions.checkArgument(!books.isEmpty());
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
            books.stream().forEach(item -> {
                if (item.getId().compareTo(book.getId()) == 0) {
                    throw new BookAlreadyOwnedException(HttpStatus.PRECONDITION_FAILED,
                            Constants.ALREADY_EXISTS);
                }
            });
            books.add(book);
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
        if (books.stream().anyMatch(item -> item.getId().compareTo(book.getId()) == 0)) {
            books.remove(book);
        } else {
            throw new BookAlreadyOwnedException(HttpStatus.NOT_FOUND, Constants.NOT_FOUND);
        }
    }
}
