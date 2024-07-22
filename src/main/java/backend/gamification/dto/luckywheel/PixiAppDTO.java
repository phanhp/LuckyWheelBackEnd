package backend.gamification.dto.luckywheel;

import backend.gamification.entity.luckywheel.Button;
import backend.gamification.entity.luckywheel.Pointer;
import backend.gamification.entity.luckywheel.Reward;
import backend.gamification.entity.luckywheel.Wheel;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PixiAppDTO {
    private long id;

    private double widthRate;
    private double heightRate;
    private String color;
    private double colorAlpha;

    private boolean pcBackgroundImageUsed;
    private String pcBackgroundSource;
    private double pcBackgroundWidthRate;
    private double pcBackgroundHeightRate;
    private double pcBackgroundBonusWidth;
    private double pcBackgroundBonusHeight;
    private double pcBackgroundX;
    private double pcBackgroundY;

    private boolean mobileBackgroundImageUsed;
    private String mobileBackgroundSource;
    private double mobileBackgroundWidthRate;
    private double mobileBackgroundHeightRate;
    private double mobileBackgroundBonusWidth;
    private double mobileBackgroundBonusHeight;
    private double mobileBackgroundX;
    private double mobileBackgroundY;

    private List<Long> wheelIdList;
    private List<Long> pointerIdList;
    private List<Long> buttonIdList;
    private List<Long> rewardIdList;
}
