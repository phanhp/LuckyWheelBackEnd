package backend.gamification.entity.luckywheel;

import backend.gamification.entity.EntityNameModel;
import backend.gamification.entity.luckywheel.PixiApp;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "rewards")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reward extends EntityNameModel {
    @Column(name = "amount")
    private long amount;

    @Column(name = "ease")
    private double ease;

    @Column(name = "limit_type")
    private String limitType;

    @Column (name = "image", columnDefinition = "LONGTEXT")
    private String image;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "pixi_app_id")
    private PixiApp pixiApp;

    public Reward(String name, long amount, double ease, String limitType, String image) {
        super(name);
        this.amount = amount;
        this.ease = ease;
        this.limitType = limitType;
        this.image = image;
    }
}
