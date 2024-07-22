package backend.gamification.repository.luckywheel;

import backend.gamification.entity.luckywheel.Button;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Repository
public interface ButtonRepository extends JpaRepository<Button, Long> {
    @Query(value = "select b.* from buttons b\n" +
            "where b.id = ?1", nativeQuery = true)
    Optional<Button> findButtonById(long buttonId);

    @Query(value = "select btn.* from buttons btn\n" +
            "inner join pixi_app p on btn.pixi_app_id = p.id\n" +
            "where p.id = ?1", nativeQuery = true)
    Optional<List<Button>> findButtonListByPixiAppId(long pixiAppId);
}
