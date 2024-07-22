package backend.gamification.repository.luckywheel;

import backend.gamification.entity.luckywheel.Reward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RewardRepository extends JpaRepository<Reward, Long> {
    @Query(value = "select r.* from rewards r\n" +
            "where r.id = ?1", nativeQuery = true)
    Optional<Reward> findRewardById(long id);

    @Query(value = "select rw.* from rewards rw\n" +
            "inner join pixi_app p on rw.pixi_app_id = p.id\n" +
            "where p.id = ?1", nativeQuery = true)
    Optional<List<Reward>> findRewardListByPixiAppId(long pixiAppId);
}
