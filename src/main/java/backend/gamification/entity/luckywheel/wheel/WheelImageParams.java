package backend.gamification.entity.luckywheel.wheel;

import backend.gamification.entity.EntityModel;
import backend.gamification.entity.luckywheel.Wheel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "wheel_image_params")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WheelImageParams extends EntityModel {
    @Column(name = "property")
    private String property = "rewardImage";

    @Column(name = "used")
    private boolean used = true;

    @Column(name = "width_rate")
    private double widthRate = 0;
    @Column(name = "heightRate")
    private double heightRate = 0;
    @Column(name = "bonus_width")
    private double bonusWidth = 40;
    @Column(name = "bonus_height")
    private double bonusHeight = 40;

    @Column(name = "percentage_distance")
    private double percentageDistance = 0.7;
    @Column(name = "bonus_distance")
    private double bonusDistance = 0;
    @Column(name = "x")
    private double x = 0;
    @Column(name = "y")
    private double y = 0;

    @Column(name = "rotate_fixed")
    private boolean rotateFixed = false;
    @Column(name = "rotate_angle")
    private double rotateAngle = 0;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "wheel_id")
    private Wheel wheel;
}
