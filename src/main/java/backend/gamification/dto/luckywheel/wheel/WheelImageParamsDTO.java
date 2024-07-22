package backend.gamification.dto.luckywheel.wheel;

import backend.gamification.entity.luckywheel.Wheel;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WheelImageParamsDTO {
    private long id;

    private String property;

    private boolean used;

    private double widthRate;
    private double heightRate;
    private double bonusWidth;
    private double bonusHeight;

    private double percentageDistance;
    private double bonusDistance;
    private double x;
    private double y;

    private boolean rotateFixed;
    private double rotateAngle;

    private long wheelId;
}
