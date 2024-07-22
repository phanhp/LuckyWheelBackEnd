package backend.gamification.entity.luckywheel;

import backend.gamification.entity.EntityModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "pixi_app")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PixiApp extends EntityModel {
    @Column(name = "width_rate")
    private double widthRate = 0.9;
    @Column(name = "height_rate")
    private double heightRate = 0.95;
    @Column(name = "color")
    private String color = "#b2b2bf";
    @Column(name = "color_alpha")
    private double colorAlpha = 0.5;

    @Column(name = "pc_background_image_used")
    private boolean pcBackgroundImageUsed = false;
    @Column(name = "pc_background_source")
    private String pcBackgroundSource = "";
    @Column(name = "pc_background_width_rate")
    private double pcBackgroundWidthRate = 1;
    @Column(name = "pc_background_height_rate")
    private double pcBackgroundHeightRate = 1;
    @Column(name = "pc_background_bonus_width")
    private double pcBackgroundBonusWidth = 0;
    @Column(name = "pc_background_bonus_height")
    private double pcBackgroundBonusHeight = 0;
    @Column(name = "pc_background_x")
    private double pcBackgroundX = 0;
    @Column(name = "pc_background_y")
    private double pcBackgroundY = 0;

    @Column(name = "mobile_background_image_used")
    private boolean mobileBackgroundImageUsed = false;
    @Column(name = "mobile_background_source")
    private String mobileBackgroundSource = "";
    @Column(name = "mobile_background_width_rate")
    private double mobileBackgroundWidthRate = 1;
    @Column(name = "mobile_background_height_rate")
    private double mobileBackgroundHeightRate = 1;
    @Column(name = "mobile_background_bonus_width")
    private double mobileBackgroundBonusWidth = 0;
    @Column(name = "mobile_background_bonus_height")
    private double mobileBackgroundBonusHeight = 0;
    @Column(name = "mobile_background_x")
    private double mobileBackgroundX = 0;
    @Column(name = "mobile_background_y")
    private double mobileBackgroundY = 0;

    @OneToMany(mappedBy = "pixiApp", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Wheel> wheelList;

    @OneToMany(mappedBy = "pixiApp", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pointer> pointerList;

    @OneToMany(mappedBy = "pixiApp", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Button> buttonList;

    @OneToMany(mappedBy = "pixiApp", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reward> rewardList;
}
