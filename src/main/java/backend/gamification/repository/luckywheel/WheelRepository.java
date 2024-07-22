package backend.gamification.repository.luckywheel;

import backend.gamification.entity.luckywheel.Wheel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WheelRepository extends JpaRepository<Wheel, Long> {
    @Query(value = "select wh.* from wheels wh\n" +
            "where wh.id = ?1", nativeQuery = true)
    Optional<Wheel> findWheelById(long id);

    @Query(value = "select w.* from wheels w\n" +
            "inner join pixi_app p on w.pixi_app_id = p.id\n" +
            "where p.id= ?1", nativeQuery = true)
    Optional<List<Wheel>> findWheelListByPixiAppId(long pixiAppId);

    @Query(value = "select wh.* from wheels wh\n" +
            "inner join wheel_color_params wcp on wh.id = wcp.wheel_id\n" +
            "where wcp.id = ?1", nativeQuery = true)
    Optional<Wheel> findWheelByWheelColorParamsId(long wheelColorParamsId);

    @Query(value = "select wh.* from wheels wh\n" +
            "inner join wheel_image_params wip on wh.id = wip.wheel_id\n" +
            "where wip.id = ?1", nativeQuery = true)
    Optional<Wheel> findWheelByWheelImageParamsId(long wheelImageParamsId);

    @Query(value = "select wh.* from wheels wh\n" +
            "inner join wheel_text_params wtp on wh.id = wtp.wheel_id\n" +
            "where wtp.id = ?1", nativeQuery = true)
    Optional<Wheel> findWheelByWheelTextParamsId(long wheelTextParamsId);
}
