package backend.gamification.repository.luckywheel;

import backend.gamification.entity.luckywheel.PixiApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PixiAppRepository extends JpaRepository<PixiApp, Long> {
    @Query(value = "select p.* from pixi_app p\n" +
            "where p.id = ?1", nativeQuery = true)
    Optional<PixiApp> findPixiAppById(long pixiAppId);

    @Query(value = "select p.* from pixi_app p\n" +
            "inner join wheels w on p.id = w.pixi_app_id\n" +
            "where w.id = ?1", nativeQuery = true)
    Optional<PixiApp> findPixiAppByWheelId(long wheelId);

    @Query(value = "select p.* from pixi_app p\n" +
            "inner join rewards rw on p.id = rw.pixi_app_id\n" +
            "where rw.id = ?1", nativeQuery = true)
    Optional<PixiApp> findPixiAppByRewardId(long rewardId);

    @Query(value = "select pa.* from pixi_app pa\n" +
            "inner join pointers ptn on pa.id = ptn.pixi_app_id\n" +
            "where ptn.id = ?1", nativeQuery = true)
    Optional<PixiApp> findPixiAppByPointerId(long pointerId);

    @Query(value = "select pa.* from pixi_app pa\n" +
            "inner join buttons btn on pa.id = btn.pixi_app_id\n" +
            "where btn.id = ?1", nativeQuery = true)
    Optional<PixiApp> findPixiAppByButtonId(long buttonId);
}
