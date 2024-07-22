package backend.gamification.dto.luckywheel;

import backend.gamification.entity.luckywheel.PixiApp;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ButtonDTO {
    private long id;

    private String text;
    private double fontSize;
    private String fontFamily;
    private String textColor;
    private String backgroundColor;
    private double width;
    private double height;
    private double roundUp;
    private double x;
    private double y;

    private String hoverText;
    private double hoverFontSize;
    private String hoverFontFamily;
    private String hoverTextColor;
    private String hoverBackgroundColor;
    private double hoverWidth;
    private double hoverHeight;
    private double hoverRoundUp;
    private double hoverX;
    private double hoverY;

    private long pixiAppId;
}
