package backend.gamification.repository.luckywheel.wheel;

import backend.gamification.entity.luckywheel.wheel.WheelTextParams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WheelTextParamsRepository extends JpaRepository<WheelTextParams, Long> {
    @Query(value = "select wtp.* from wheel_text_params wtp\n" +
            "inner join wheels w on wtp.wheel_id = w.id\n" +
            "where w.id = ?1", nativeQuery = true)
    Optional<List<WheelTextParams>> findWheelTextParamsListByWheelId(long wheelId);

    @Query(value = "select wtp.* from wheel_text_params wtp\n" +
            "inner join wheels wh on wtp.wheel_id = wh.id\n" +
            "inner join pixi_app pa on wh.pixi_app_id = pa.id\n" +
            "where pa.id = ?1", nativeQuery = true)
    Optional<List<WheelTextParams>> findWheelTextParamsListByPixiAppId(long pixiAppId);
}
