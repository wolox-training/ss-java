package wolox.training.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
    @Query(value = "SELECT u FROM User u WHERE u.userName = ?1")
    User findByUserName(String userName);
}
