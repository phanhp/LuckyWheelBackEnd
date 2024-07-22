package backend.gamification.dto.luckywheel;

import backend.gamification.entity.luckywheel.PixiApp;
import backend.gamification.entity.luckywheel.wheel.WheelColorParams;
import backend.gamification.entity.luckywheel.wheel.WheelImageParams;
import backend.gamification.entity.luckywheel.wheel.WheelTextParams;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WheelDTO {
    private long id;

    private double containerX;
    private double containerY;

    private double wheelRadiusRate;
    private double wheelRadiusBonus;
    private boolean wheelRadiusFixed;

    private boolean backgroundUsed;
    private String backgroundSource;
    private double backgroundWidthRate;
    private double backgroundHeightRate;
    private double backgroundBonusWidth;
    private double backgroundBonusHeight;
    private double backgroundWidthFixed;
    private double backgroundHeightFixed;

    private List<Long> wheelColorParamsIdList;

    private List<Long> wheelTextParamsIdList;

    private List<Long> wheelImageParamsIdList;

    private long pixiAppId;
}
