package backend.gamification.repository.luckywheel;

import backend.gamification.entity.luckywheel.Pointer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PointerRepository extends JpaRepository<Pointer, Long> {
    @Query(value = "select pnt.* from pointers pnt\n" +
            "inner join pixi_app p on pnt.pixi_app_id = p.id\n" +
            "where p.id = ?1", nativeQuery = true)
    Optional<List<Pointer>> findPointerListByPixiAppId (long pixiAppId);
}
