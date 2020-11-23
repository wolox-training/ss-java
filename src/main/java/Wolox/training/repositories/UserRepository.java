package Wolox.training.repositories;

import Wolox.training.models.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * This method find user by userName
     *
     * @param userName
     * @return
     */
    Optional<User> findByName(String userName);
}
