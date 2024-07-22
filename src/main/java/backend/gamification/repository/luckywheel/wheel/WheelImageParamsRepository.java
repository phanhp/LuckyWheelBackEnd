package backend.gamification.repository.luckywheel.wheel;

import backend.gamification.entity.luckywheel.wheel.WheelImageParams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WheelImageParamsRepository extends JpaRepository<WheelImageParams, Long> {
    @Query(value = "select wip.* from wheel_image_params wip\n" +
            "where wip.id = ?1", nativeQuery = true)
    Optional<WheelImageParams> findWheelImageParamsById(long id);

    @Query(value = "select wip.* from wheel_image_params wip\n" +
            "inner join wheels w on wip.wheel_id = w.id\n" +
            "where w.id = ?1", nativeQuery = true)
    Optional<List<WheelImageParams>> findWheelImageParamsListByWheelId(long wheelId);

    @Query(value = "select wip.* from wheel_image_params wip\n" +
            "inner join wheels wh on wip.wheel_id = wh.id\n" +
            "inner join pixi_app pa on wh.pixi_app_id = pa.id\n" +
            "where pa.id = ?1", nativeQuery = true)
    Optional<List<WheelImageParams>> findWheelImageParamsListByPixiAppId(long pixiAppId);
}
