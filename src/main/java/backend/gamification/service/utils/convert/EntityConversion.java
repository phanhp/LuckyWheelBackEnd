package backend.gamification.service.utils.convert;

import backend.gamification.dto.luckywheel.ButtonDTO;
import backend.gamification.dto.luckywheel.RewardDTO;
import backend.gamification.dto.luckywheel.wheel.WheelColorParamsDTO;
import backend.gamification.entity.luckywheel.Button;
import backend.gamification.entity.luckywheel.PixiApp;
import backend.gamification.entity.luckywheel.Reward;
import backend.gamification.entity.luckywheel.Wheel;
import backend.gamification.entity.luckywheel.wheel.WheelColorParams;
import backend.gamification.entity.luckywheel.wheel.WheelTextParams;
import backend.gamification.service.RepositoryCaller;
import backend.gamification.service.utils.Utils;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EntityConversion {
    @Autowired
    Utils utils;
    @Autowired
    RepositoryCaller repositoryCaller;

    //BUTTON
    public Button convertButtonDTOToButton(ButtonDTO buttonDTO) {
        if (buttonDTO == null) {
            return null;
        }
        Button button = repositoryCaller.getButtonRepository().findButtonById(buttonDTO.getId()).orElse(new Button());
        button.setText(buttonDTO.getText());
        button.setFontSize(buttonDTO.getFontSize());
        button.setFontFamily(buttonDTO.getFontFamily());
        button.setTextColor(buttonDTO.getTextColor());
        button.setBackgroundColor(buttonDTO.getBackgroundColor());
        button.setWidth(buttonDTO.getWidth());
        button.setHeight(buttonDTO.getHeight());
        button.setRoundUp(buttonDTO.getRoundUp());
        button.setX(buttonDTO.getX());
        button.setY(buttonDTO.getY());

        button.setHoverText(buttonDTO.getHoverText());
        button.setHoverFontSize(buttonDTO.getHoverFontSize());
        button.setHoverFontFamily(buttonDTO.getHoverFontFamily());
        button.setHoverTextColor(buttonDTO.getHoverTextColor());
        button.setBackgroundColor(buttonDTO.getBackgroundColor());
        button.setHoverWidth(buttonDTO.getHoverWidth());
        button.setHoverHeight(buttonDTO.getHoverHeight());
        button.setHoverRoundUp(buttonDTO.getHoverRoundUp());
        button.setHoverX(buttonDTO.getHoverX());
        button.setHoverY(buttonDTO.getHoverY());

        PixiApp pixiApp = repositoryCaller.getPixiAppRepository().findPixiAppById(buttonDTO.getPixiAppId()).orElse(null);
        button.setPixiApp(pixiApp);

        return button;
    }

    public List<Button> convertButtonDTOListToButtonList(List<ButtonDTO> buttonDTOList) {
        if (buttonDTOList == null) {
            return new ArrayList<>();
        }
        return buttonDTOList.stream().map(this::convertButtonDTOToButton).collect(Collectors.toList());
    }

    //REWARD
    public Reward convertRewardDTOToReward(RewardDTO rewardDTO) {
        if (rewardDTO == null) {
            return null;
        }
        Reward reward = repositoryCaller.getRewardRepository().findRewardById(rewardDTO.getId()).orElse(new Reward());
        reward.setName(rewardDTO.getName());
        reward.setAmount(rewardDTO.getAmount());
        reward.setEase(rewardDTO.getEase());
        reward.setLimitType(reward.getLimitType());
        reward.setImage(rewardDTO.getImage());

        PixiApp pixiApp = repositoryCaller.getPixiAppRepository().findPixiAppById(rewardDTO.getPixiAppId()).orElse(null);
        reward.setPixiApp(pixiApp);

        return reward;
    }

    public List<Reward> convertRewardDTOListToRewardList(List<RewardDTO> rewardDTOList) {
        if (rewardDTOList == null) {
            return new ArrayList<>();
        }
        return rewardDTOList.stream().map(this::convertRewardDTOToReward).collect(Collectors.toList());
    }

    //WHEEL_COLOR_PARAMS
    public WheelColorParams converWheelColorParamsDTOToWheelColorParams(WheelColorParamsDTO wheelColorParamsDTO) {
        if (wheelColorParamsDTO == null) {
            return null;
        }
        WheelColorParams wheelColorParams = repositoryCaller.getWheelColorParamsRepository().findWheelColorParamsById(wheelColorParamsDTO.getId()).orElse(new WheelColorParams());

        wheelColorParams.setColorData(wheelColorParamsDTO.getColorData());
        wheelColorParams.setColorAlpha(wheelColorParamsDTO.getColorAlpha());

        Wheel wheel = repositoryCaller.getWheelRepository().findWheelById(wheelColorParamsDTO.getWheelId()).orElse(null);
        wheelColorParams.setWheel(wheel);
        return wheelColorParams;
    }

    public List<WheelColorParams> convertWheelColorParamsDTOListToWheelColorParamsList (List<WheelColorParamsDTO> wheelColorParamsDTOList){
        if(wheelColorParamsDTOList == null){
            return new ArrayList<>();
        }
        return wheelColorParamsDTOList.stream().map(this::converWheelColorParamsDTOToWheelColorParams).collect(Collectors.toList());
    }
    //USER
}
