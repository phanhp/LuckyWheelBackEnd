package backend.gamification.entity.luckywheel.wheel;

import backend.gamification.entity.EntityModel;
import backend.gamification.entity.luckywheel.Wheel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "wheel_color_params")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WheelColorParams extends EntityModel {
    @Column(name = "color_data")
    private String colorData;
    @Column(name = "color_alpha")
    private double colorAlpha = 0.5;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "wheel_id")
    private Wheel wheel;
}
