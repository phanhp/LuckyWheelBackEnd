package backend.gamification.repository.user;

import backend.gamification.entity.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query(value = "select r.* from  roles r\n" +
            "join user_role ur on r.id = ur.role_id\n" +
            "join users u on ur.user_id = u.id\n" +
            "where u.id = ?1", nativeQuery = true)
    Optional<List<Role>> findRoleListByUserId(long userId);

    Optional<Role> findByName(String roleName);

    boolean existsByName(String roleName);

}
