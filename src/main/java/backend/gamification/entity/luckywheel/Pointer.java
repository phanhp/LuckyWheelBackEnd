package backend.gamification.entity.luckywheel;

import backend.gamification.entity.EntityModel;
import backend.gamification.entity.luckywheel.PixiApp;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pointers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pointer extends EntityModel {
    @Column(name = "pointer_source")
    private String pointerSource = "public/assets/Pointer.png";

    @Column(name = "width_rate")
    private double widthRate = 0;
    @Column(name = "height_rate")
    private double heightRate = 0;
    @Column(name = "bonus_width")
    private double bonusWidth = 70;
    @Column(name = "bonus_height")
    private double bonusHeight = 70;
    @Column(name = "pointer_x")
    private double pointerX = 0;
    @Column(name = "pointer_y")
    private double pointerY = -10;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "pixi_app_id")
    private PixiApp pixiApp;
}
