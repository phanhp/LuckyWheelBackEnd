package backend.gamification.repository.luckywheel.wheel;

import backend.gamification.entity.luckywheel.wheel.WheelColorParams;
import backend.gamification.entity.luckywheel.wheel.WheelTextParams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WheelColorParamsRepository extends JpaRepository<WheelColorParams, Long> {
    @Query(value = "select wcp.* from wheel_color_params wcp\n" +
            "inner join wheels w on wcp.wheel_id = w.id\n" +
            "where w.id = ?1", nativeQuery = true)
    Optional<List<WheelColorParams>> findWheelColorParamsListByWheelId(long wheelId);

    @Query(value = "select wcp.* from wheel_color_params wcp\n" +
            "inner join wheels wh on wcp.wheel_id = wh.id\n" +
            "inner join pixi_app pa on wh.pixi_app_id = pa.id\n" +
            "where pa.id = ?1", nativeQuery = true)
    Optional<List<WheelColorParams>> findWheelColorParamsListByPixiAppId(long pixiAppId);

    @Query(value = "select wcp.* from wheel_color_params wcp\n" +
            "where wcp.id = ?1", nativeQuery = true)
    Optional<WheelColorParams> findWheelColorParamsById(long id);
}
