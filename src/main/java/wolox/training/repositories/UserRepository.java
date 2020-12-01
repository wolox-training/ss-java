package wolox.training.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import wolox.training.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * This method find user by name
     *
     * @param name
     * @return
     */
    Optional<User> findByName(String name);

    /**
     * This method find user by userName
     *
     * @param userName
     * @return
     */
    Optional<User> findByUserName(String userName);

    /**
     * This method find Users by range of dates and sequence name
     *
     * @param initDate
     * @param finalDate
     * @param sequenceName
     * @return
     */
    List<User> findByBirthdateBetweenAndNameContainingIgnoreCase(LocalDate initDate,
            LocalDate finalDate, String sequenceName);
}
