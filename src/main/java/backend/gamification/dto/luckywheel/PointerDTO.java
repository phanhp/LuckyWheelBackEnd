package backend.gamification.dto.luckywheel;

import backend.gamification.entity.luckywheel.PixiApp;
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
public class PointerDTO {
    private long id;

    private String pointerSource;

    private double widthRate;
    private double heightRate;
    private double bonusWidth;
    private double bonusHeight;
    private double pointerX;
    private double pointerY;

    private long pixiAppId;
}
