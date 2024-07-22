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
public class WheelTextParamsDTO {
    private long id;

    private String property;
    private boolean used;
    private String fontFamily;
    private String color;
    private double fontSize;
    private boolean rotateFixed;
    private double rotate;
    private double percentageDistance;
    private double bonusDistance;
    private double textX;
    private double textY;

    private long wheelId;
}
