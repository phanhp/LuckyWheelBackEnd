package backend.gamification.entity.luckywheel.wheel;

import backend.gamification.entity.EntityModel;
import backend.gamification.entity.luckywheel.Wheel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "wheel_text_params")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WheelTextParams extends EntityModel {
    @Column(name = "property")
    private String property = "rewardName";
    @Column(name = "used")
    private boolean used = true;
    @Column(name = "font_family")
    private String fontFamily = "Arial";
    @Column(name = "color")
    private String color = "#030330";
    @Column(name = "font_size")
    private double fontSize = 12;
    @Column(name = "rotate_fixed")
    private boolean rotateFixed = false;
    @Column(name = "rotate")
    private double rotate = 0;
    @Column(name = "percentage_distance")
    private double percentageDistance = 0.9;
    @Column(name = "bonus_distance")
    private double bonusDistance = 0;
    @Column(name = "text_x")
    private double textX = 0;
    @Column(name = "text_y")
    private double textY = 0;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "wheel_id")
    private Wheel wheel;
}
