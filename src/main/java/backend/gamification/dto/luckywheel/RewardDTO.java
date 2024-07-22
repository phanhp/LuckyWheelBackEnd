package backend.gamification.dto.luckywheel;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RewardDTO {
    private long id;
    private String name;
    private long amount;
    private double ease;
    private String limitType;
    private String image;
    private long pixiAppId;
}
