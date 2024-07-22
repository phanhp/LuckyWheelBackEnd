package backend.gamification.entity.luckywheel;

import backend.gamification.entity.EntityModel;
import backend.gamification.entity.luckywheel.wheel.WheelColorParams;
import backend.gamification.entity.luckywheel.wheel.WheelImageParams;
import backend.gamification.entity.luckywheel.wheel.WheelTextParams;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "wheels")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Wheel extends EntityModel {
    @Column(name = "container_x")
    private double containerX = 0;
    @Column(name = "container_y")
    private double containerY = 0;

    @Column(name = "wheel_radius_rate")
    private double wheelRadiusRate = 0.4;
    @Column(name = "wheel_radius_bonus")
    private double wheelRadiusBonus = 0;
    @Column(name = "wheel_radius_fixed")
    private boolean wheelRadiusFixed = false;

    @Column(name = "background_used")
    private boolean backgroundUsed = true;
    @Column(name = "background_source")
    private String backgroundSource = "public/assets/roll.png";
    @Column(name = "background_width_rate")
    private double backgroundWidthRate = 1;
    @Column(name = "background_height_rate")
    private double backgroundHeightRate = 1;
    @Column(name = "background_bonus_width")
    private double backgroundBonusWidth = 0;
    @Column(name = "background_bonus_height")
    private double backgroundBonusHeight = 0;
    @Column(name = "background_width_fixed")
    private double backgroundWidthFixed = 0;
    @Column(name = "background_height_fixed")
    private double backgroundHeightFixed = 0;

    @OneToMany(mappedBy = "wheel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<WheelColorParams> wheelColorParamsList;

    @OneToMany(mappedBy = "wheel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<WheelTextParams> wheelTextParamsList;

    @OneToMany(mappedBy = "wheel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<WheelImageParams> wheelImageParamsList;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "pixi_app_id")
    private PixiApp pixiApp;
}
