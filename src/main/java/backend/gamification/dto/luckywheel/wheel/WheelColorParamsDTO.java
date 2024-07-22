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
public class WheelColorParamsDTO {
    private long id;

    private String colorData;
    private double colorAlpha;

    private long wheelId;

    public WheelColorParamsDTO(String colorData, double colorAlpha, long wheelId) {
        this.colorData = colorData;
        this.colorAlpha = colorAlpha;
        this.wheelId = wheelId;
    }
}
