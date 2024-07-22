package backend.gamification.repository.user;


import backend.gamification.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select u.* from users u\n" +
            "where u.username = ?1", nativeQuery = true)
    Optional<User> findUserByUsername(String username);

    boolean existsByUsername(String username);

}
