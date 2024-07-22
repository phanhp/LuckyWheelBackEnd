package backend.gamification.entity.luckywheel;

import backend.gamification.entity.EntityModel;
import backend.gamification.entity.luckywheel.PixiApp;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "buttons")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Button extends EntityModel {
    @Column(name = "text")
    private String text = "LET PLAY!!!";
    @Column(name = "font_size")
    private double fontSize = 20;
    @Column(name = "font_family")
    private String fontFamily = "Arial";
    @Column(name = "text_color")
    private String textColor = "#e6d8ca";
    @Column(name = "background_color")
    private String backgroundColor = "#030330";
    @Column(name = "width")
    private double width = 160;
    @Column(name = "height")
    private double height = 50;
    @Column(name = "round_up")
    private double roundUp = 15;
    @Column(name = "x")
    private double x = 0;
    @Column(name = "y")
    private double y = 0;

    @Column(name = "hover_text")
    private String hoverText = "SPIN!!!";
    @Column(name = "hover_font_size")
    private double hoverFontSize = 25;
    @Column(name = "hover_font_family")
    private String hoverFontFamily = "Arial";
    @Column(name = "hover_text_color")
    private String hoverTextColor = "#e6d8ca";
    @Column(name = "hover_background_color")
    private String hoverBackgroundColor = "#030330";
    @Column(name = "hover_width")
    private double hoverWidth = 165;
    @Column(name = "hover_height")
    private double hoverHeight = 55;
    @Column(name = "hover_round_up")
    private double hoverRoundUp = 15;
    @Column(name = "hover_x")
    private double hoverX = 0;
    @Column(name = "hover_y")
    private double hoverY = 0;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "pixi_app_id")
    private PixiApp pixiApp;
}
