package Wolox.training.controllers;

import Wolox.training.commons.Constants;
import Wolox.training.models.Book;
import Wolox.training.models.User;
import Wolox.training.repositories.BookRepository;
import Wolox.training.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    /**
     * This method create a user
     *
     * @param user
     * @return
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user) {
        return userRepository.save(user);
    }

    /**
     * This method delete a user
     *
     * @param id
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                Constants.USER_NOT_FOUND));
        userRepository.deleteById(id);
    }

    /**
     * this method update information the user
     *
     * @param user
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    public User update(@RequestBody User user, @PathVariable Long id) {
        if (user.getId() != id) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Constants.USER_INCORRECT);
        }
        userRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                Constants.USER_NOT_FOUND));
        return userRepository.save(user);
    }

    /**
     * This method find all users
     *
     * @return
     */
    @GetMapping
    public Iterable findAll() {
        return userRepository.findAll();
    }

    /**
     * This method find a user by id
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public User findOne(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                Constants.USER_NOT_FOUND));
    }

    /**
     * This method create a book from user
     * @param idBook
     * @param idUser
     */
    @PostMapping("book/{idBook}/user/{idUser}")
    public void createUserBook(@PathVariable(name = "idBook") Long idBook,
            @PathVariable(name = "idUser") Long idUser) {
        User user = userRepository.findById(idUser).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        Constants.USER_NOT_FOUND));
        Book book = bookRepository.findById(idBook).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        Constants.NOT_FOUND));

        ((User) user).addBook(book);
        userRepository.save(user);
    }

    /**
     * This method remove a book from user
     * @param idBook
     * @param idUser
     */
    @DeleteMapping("book/{idBook}/user/{idUser}")
    public void removeUserBook(@PathVariable(name = "idBook") Long idBook,
            @PathVariable(name = "idUser") Long idUser) {
        User user = userRepository.findById(idUser).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        Constants.USER_NOT_FOUND));
        Book book = bookRepository.findById(idBook).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        Constants.NOT_FOUND));

        ((User) user).removeBook(book);
        userRepository.save(user);
    }
}