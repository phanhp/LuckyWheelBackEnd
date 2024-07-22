package backend.gamification.service.utils.convert;

import backend.gamification.dto.luckywheel.*;
import backend.gamification.dto.luckywheel.wheel.WheelColorParamsDTO;
import backend.gamification.dto.luckywheel.wheel.WheelImageParamsDTO;
import backend.gamification.dto.luckywheel.wheel.WheelTextParamsDTO;
import backend.gamification.dto.user.UserDTO;
import backend.gamification.entity.luckywheel.*;
import backend.gamification.entity.luckywheel.wheel.WheelColorParams;
import backend.gamification.entity.luckywheel.wheel.WheelImageParams;
import backend.gamification.entity.luckywheel.wheel.WheelTextParams;
import backend.gamification.entity.user.Role;
import backend.gamification.entity.user.User;
import backend.gamification.service.RepositoryCaller;
import backend.gamification.service.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DTOConversion {
    @Autowired
    Utils utils;
    @Autowired
    RepositoryCaller repositoryCaller;

    //PIXI_APP
    public PixiAppDTO convertPixiAppToPixiAppDTO(PixiApp pixiApp) {
        if (pixiApp == null) {
            return new PixiAppDTO();
        }

        PixiAppDTO pixiAppDTO = new PixiAppDTO();
        pixiAppDTO.setId(pixiApp.getId());

        pixiAppDTO.setWidthRate(pixiApp.getWidthRate());
        pixiAppDTO.setHeightRate(pixiApp.getHeightRate());
        pixiAppDTO.setColor(pixiApp.getColor());
        pixiAppDTO.setColorAlpha(pixiApp.getColorAlpha());

        pixiAppDTO.setPcBackgroundImageUsed(pixiApp.isPcBackgroundImageUsed());
        pixiAppDTO.setPcBackgroundSource(pixiApp.getPcBackgroundSource());
        pixiAppDTO.setPcBackgroundWidthRate(pixiApp.getPcBackgroundWidthRate());
        pixiAppDTO.setPcBackgroundHeightRate(pixiApp.getPcBackgroundHeightRate());
        pixiAppDTO.setPcBackgroundBonusWidth(pixiApp.getPcBackgroundBonusWidth());
        pixiAppDTO.setPcBackgroundBonusHeight(pixiApp.getPcBackgroundBonusHeight());
        pixiAppDTO.setPcBackgroundX(pixiApp.getPcBackgroundX());
        pixiAppDTO.setPcBackgroundY(pixiApp.getPcBackgroundY());

        pixiAppDTO.setMobileBackgroundImageUsed(pixiApp.isMobileBackgroundImageUsed());
        pixiAppDTO.setMobileBackgroundSource(pixiApp.getMobileBackgroundSource());
        pixiAppDTO.setMobileBackgroundWidthRate(pixiApp.getMobileBackgroundWidthRate());
        pixiAppDTO.setMobileBackgroundHeightRate(pixiApp.getMobileBackgroundHeightRate());
        pixiAppDTO.setMobileBackgroundBonusWidth(pixiApp.getPcBackgroundBonusWidth());
        pixiAppDTO.setMobileBackgroundBonusHeight(pixiApp.getMobileBackgroundBonusHeight());
        pixiAppDTO.setMobileBackgroundX(pixiApp.getMobileBackgroundX());
        pixiAppDTO.setMobileBackgroundY(pixiApp.getMobileBackgroundY());

        List<Wheel> wheelList = repositoryCaller.getWheelRepository().findWheelListByPixiAppId(pixiApp.getId()).orElse(new ArrayList<>());
        pixiAppDTO.setWheelIdList(wheelList.stream().map(Wheel::getId).collect(Collectors.toList()));

        List<Pointer> pointerList = repositoryCaller.getPointerRepository().findPointerListByPixiAppId(pixiApp.getId()).orElse(new ArrayList<>());
        pixiAppDTO.setPointerIdList(pointerList.stream().map(Pointer::getId).collect(Collectors.toList()));

        List<Button> buttonList = repositoryCaller.getButtonRepository().findButtonListByPixiAppId(pixiApp.getId()).orElse(new ArrayList<>());
        pixiAppDTO.setButtonIdList(buttonList.stream().map(Button::getId).collect(Collectors.toList()));

        List<Reward> rewardList = repositoryCaller.getRewardRepository().findRewardListByPixiAppId(pixiApp.getId()).orElse(new ArrayList<>());
        pixiAppDTO.setRewardIdList(rewardList.stream().map(Reward::getId).collect(Collectors.toList()));

        return pixiAppDTO;
    }

    //WHEEL
    public WheelDTO convertWheelToWheelDTO(Wheel wheel) {
        if (wheel == null) {
            return new WheelDTO();
        }

        WheelDTO wheelDTO = new WheelDTO();
        wheelDTO.setId(wheel.getId());

        wheelDTO.setContainerX(wheel.getContainerX());
        wheelDTO.setContainerY(wheel.getContainerY());

        wheelDTO.setWheelRadiusRate(wheel.getWheelRadiusRate());
        wheelDTO.setWheelRadiusBonus(wheel.getWheelRadiusBonus());
        wheelDTO.setWheelRadiusFixed(wheel.isWheelRadiusFixed());

        wheelDTO.setBackgroundUsed(wheel.isBackgroundUsed());
        wheelDTO.setBackgroundSource(wheel.getBackgroundSource());
        wheelDTO.setBackgroundWidthRate(wheel.getBackgroundWidthRate());
        wheelDTO.setBackgroundHeightRate(wheel.getBackgroundHeightRate());
        wheelDTO.setBackgroundBonusWidth(wheel.getBackgroundBonusWidth());
        wheelDTO.setBackgroundBonusHeight(wheel.getBackgroundBonusHeight());
        wheelDTO.setBackgroundWidthFixed(wheel.getBackgroundWidthFixed());
        wheelDTO.setBackgroundHeightFixed(wheel.getBackgroundHeightFixed());

        List<WheelColorParams> wheelColorParamsList = repositoryCaller.getWheelColorParamsRepository().findWheelColorParamsListByWheelId(wheel.getId()).orElse(new ArrayList<>());
        wheelDTO.setWheelColorParamsIdList(wheelColorParamsList.stream().map(WheelColorParams::getId).collect(Collectors.toList()));

        List<WheelTextParams> wheelTextParamsList = repositoryCaller.getWheelTextParamsRepository().findWheelTextParamsListByWheelId(wheel.getId()).orElse(new ArrayList<>());
        wheelDTO.setWheelTextParamsIdList(wheelTextParamsList.stream().map(WheelTextParams::getId).collect(Collectors.toList()));

        List<WheelImageParams> wheelImageParamsList = repositoryCaller.getWheelImageParamsRepository().findWheelImageParamsListByWheelId(wheel.getId()).orElse(new ArrayList<>());
        wheelDTO.setWheelImageParamsIdList(wheelImageParamsList.stream().map(WheelImageParams::getId).collect(Collectors.toList()));

        PixiApp pixiApp = repositoryCaller.getPixiAppRepository().findPixiAppByWheelId(wheel.getId()).orElse(new PixiApp());
        wheelDTO.setPixiAppId(pixiApp.getId());

        return wheelDTO;
    }

    public List<WheelDTO> convertWheelListToWheelDTOList(List<Wheel> wheelList) {
        if (wheelList == null) {
            return new ArrayList<>();
        }
        return wheelList.stream().map(this::convertWheelToWheelDTO).collect(Collectors.toList());
    }

    //POINTER
    public PointerDTO convertPointerToPointerDTO(Pointer pointer) {
        if (pointer == null) {
            return new PointerDTO();
        }

        PointerDTO pointerDTO = new PointerDTO();
        pointerDTO.setId(pointer.getId());

        pointerDTO.setPointerSource(pointer.getPointerSource());

        pointerDTO.setWidthRate(pointer.getWidthRate());
        pointerDTO.setHeightRate(pointer.getHeightRate());
        pointerDTO.setBonusWidth(pointer.getBonusWidth());
        pointerDTO.setBonusHeight(pointer.getBonusHeight());
        pointerDTO.setPointerX(pointer.getPointerX());
        pointerDTO.setPointerY(pointer.getPointerY());

        PixiApp pixiApp = repositoryCaller.getPixiAppRepository().findPixiAppByPointerId(pointer.getId()).orElse(new PixiApp());
        pointerDTO.setPixiAppId(pixiApp.getId());

        return pointerDTO;
    }

    public List<PointerDTO> convertPointerListToPointerDTOList(List<Pointer> pointerList) {
        if (pointerList == null) {
            return new ArrayList<>();
        }
        return pointerList.stream().map(this::convertPointerToPointerDTO).collect(Collectors.toList());
    }

    //BUTTON
    public ButtonDTO convertButtonToButtonDTO(Button button) {
        if (button == null) {
            return new ButtonDTO();
        }

        ButtonDTO buttonDTO = new ButtonDTO();

        buttonDTO.setId(button.getId());

        buttonDTO.setText(button.getText());
        buttonDTO.setFontSize(button.getFontSize());
        buttonDTO.setFontFamily(button.getFontFamily());
        buttonDTO.setTextColor(button.getTextColor());
        buttonDTO.setBackgroundColor(button.getBackgroundColor());
        buttonDTO.setWidth(button.getWidth());
        buttonDTO.setHeight(button.getHeight());
        buttonDTO.setRoundUp(button.getRoundUp());
        buttonDTO.setX(button.getX());
        buttonDTO.setY(button.getY());

        buttonDTO.setHoverText(button.getHoverText());
        buttonDTO.setHoverFontSize(button.getHoverFontSize());
        buttonDTO.setHoverFontFamily(button.getHoverFontFamily());
        buttonDTO.setHoverTextColor(button.getHoverTextColor());
        buttonDTO.setHoverBackgroundColor(button.getHoverBackgroundColor());
        buttonDTO.setHoverWidth(button.getHoverWidth());
        buttonDTO.setHoverHeight(button.getHoverHeight());
        buttonDTO.setHoverRoundUp(button.getHoverRoundUp());
        buttonDTO.setHoverX(button.getHoverX());
        buttonDTO.setHoverY(button.getHoverY());

        PixiApp pixiApp = repositoryCaller.getPixiAppRepository().findPixiAppByButtonId(button.getId()).orElse(new PixiApp());
        buttonDTO.setPixiAppId(pixiApp.getId());

        return buttonDTO;
    }

    public List<ButtonDTO> convertButtonListToButtonDTOList(List<Button> buttonList) {
        if (buttonList == null) {
            return new ArrayList<>();
        }
        return buttonList.stream().map(this::convertButtonToButtonDTO).collect(Collectors.toList());
    }

    //REWARD
    public RewardDTO convertRewardToRewardDTO(Reward reward) {
        if (reward == null) {
            return new RewardDTO();
        }
        RewardDTO rewardDTO = new RewardDTO();
        rewardDTO.setId(reward.getId());
        rewardDTO.setName(reward.getName());
        rewardDTO.setAmount(reward.getAmount());
        rewardDTO.setEase(reward.getEase());
        rewardDTO.setLimitType(reward.getLimitType());
        rewardDTO.setImage(reward.getImage());

        PixiApp pixiApp = repositoryCaller.getPixiAppRepository().findPixiAppByRewardId(reward.getId()).orElse(new PixiApp());
        rewardDTO.setPixiAppId(pixiApp.getId());

        return rewardDTO;
    }

    public List<RewardDTO> convertRewardListToRewardDTOList(List<Reward> rewardList) {
        if (rewardList == null) {
            return new ArrayList<>();
        }
        return rewardList.stream().map(this::convertRewardToRewardDTO).collect(Collectors.toList());
    }

    //WHEEL_COLOR_PARAMS
    public WheelColorParamsDTO convertWheelColorParamsToWheelColorParamsDTO(WheelColorParams wheelColorParams) {
        if (wheelColorParams == null) {
            return new WheelColorParamsDTO();
        }

        WheelColorParamsDTO wheelColorParamsDTO = new WheelColorParamsDTO();

        wheelColorParamsDTO.setId(wheelColorParams.getId());

        wheelColorParamsDTO.setColorData(wheelColorParams.getColorData());
        wheelColorParamsDTO.setColorAlpha(wheelColorParams.getColorAlpha());

        Wheel wheel = repositoryCaller.getWheelRepository().findWheelByWheelColorParamsId(wheelColorParams.getId()).orElse(new Wheel());
        wheelColorParamsDTO.setWheelId(wheel.getId());

        return wheelColorParamsDTO;
    }

    public List<WheelColorParamsDTO> convertWheelColorParamsListToWheelColorParamsDTOList(List<WheelColorParams> wheelColorParamsList) {
        if (wheelColorParamsList == null) {
            return new ArrayList<>();
        }

        return wheelColorParamsList.stream().map(this::convertWheelColorParamsToWheelColorParamsDTO).collect(Collectors.toList());
    }

    //WHEEL_IMAGE_PARAMS
    public WheelImageParamsDTO convertWheelImageParamsToWheelImageParamsDTO(WheelImageParams wheelImageParams) {
        if (wheelImageParams == null) {
            return new WheelImageParamsDTO();
        }

        WheelImageParamsDTO wheelImageParamsDTO = new WheelImageParamsDTO();

        wheelImageParamsDTO.setId(wheelImageParams.getId());

        wheelImageParamsDTO.setProperty(wheelImageParams.getProperty());

        wheelImageParamsDTO.setUsed(wheelImageParams.isUsed());

        wheelImageParamsDTO.setWidthRate(wheelImageParams.getWidthRate());
        wheelImageParamsDTO.setHeightRate(wheelImageParams.getHeightRate());
        wheelImageParamsDTO.setBonusWidth(wheelImageParams.getBonusWidth());
        wheelImageParamsDTO.setBonusHeight(wheelImageParams.getBonusHeight());

        wheelImageParamsDTO.setPercentageDistance(wheelImageParams.getPercentageDistance());
        wheelImageParamsDTO.setBonusDistance(wheelImageParams.getBonusDistance());
        wheelImageParamsDTO.setX(wheelImageParams.getX());
        wheelImageParamsDTO.setY(wheelImageParams.getY());

        wheelImageParamsDTO.setRotateFixed(wheelImageParams.isRotateFixed());
        wheelImageParamsDTO.setRotateAngle(wheelImageParams.getRotateAngle());

        Wheel wheel = repositoryCaller.getWheelRepository().findWheelByWheelImageParamsId(wheelImageParams.getId()).orElse(new Wheel());
        wheelImageParamsDTO.setWheelId(wheel.getId());

        return wheelImageParamsDTO;
    }

    public List<WheelImageParamsDTO> convertWheelImageParamsListToWheelImageParamsDTOList(List<WheelImageParams> wheelImageParamsList) {
        return wheelImageParamsList.stream().map(this::convertWheelImageParamsToWheelImageParamsDTO).collect(Collectors.toList());
    }

    //WHEEL_TEXT_PARAMS
    public WheelTextParamsDTO convertWheelTextParamsToWheelTextParamsDTO(WheelTextParams wheelTextParams) {
        if (wheelTextParams == null) {
            return new WheelTextParamsDTO();
        }

        WheelTextParamsDTO wheelTextParamsDTO = new WheelTextParamsDTO();

        wheelTextParamsDTO.setId(wheelTextParams.getId());

        wheelTextParamsDTO.setProperty(wheelTextParams.getProperty());
        wheelTextParamsDTO.setUsed(wheelTextParams.isUsed());
        wheelTextParamsDTO.setFontFamily(wheelTextParams.getFontFamily());
        wheelTextParamsDTO.setColor(wheelTextParams.getColor());
        wheelTextParamsDTO.setFontSize(wheelTextParams.getFontSize());
        wheelTextParamsDTO.setRotateFixed(wheelTextParams.isRotateFixed());
        wheelTextParamsDTO.setRotate(wheelTextParams.getRotate());
        wheelTextParamsDTO.setPercentageDistance(wheelTextParams.getPercentageDistance());
        wheelTextParamsDTO.setBonusDistance(wheelTextParams.getBonusDistance());
        wheelTextParamsDTO.setTextX(wheelTextParams.getTextX());
        wheelTextParamsDTO.setTextY(wheelTextParams.getTextY());

        Wheel wheel = repositoryCaller.getWheelRepository().findWheelByWheelTextParamsId(wheelTextParams.getId()).orElse(new Wheel());
        wheelTextParamsDTO.setWheelId(wheel.getId());

        return wheelTextParamsDTO;
    }

    public List<WheelTextParamsDTO> convertWheelTextParamsListToWheelTextParamsDTOList(List<WheelTextParams> wheelTextParamsList){
        if (wheelTextParamsList == null){
            return new ArrayList<>();
        }
        return wheelTextParamsList.stream().map(this::convertWheelTextParamsToWheelTextParamsDTO).collect(Collectors.toList());
    }

    //USER
    public UserDTO convertUserToUserDTO(User user) {
        if (user == null) {
            return new UserDTO();
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setName(user.getName());
        userDTO.setDob(utils.convertLocalDateToString(user.getDob()));
        userDTO.setActive(utils.convertBooleanToString(user.isActive()));
        userDTO.setAvailable(utils.convertBooleanToString(user.isAvailable()));

        List<Role> roleList = repositoryCaller.getRoleRepository().findRoleListByUserId(user.getId()).orElse(new ArrayList<>());
        List<String> roleNameList = roleList.stream().map(Role::getName).collect(Collectors.toList());
        List<Long> roleIdList = roleList.stream().map(Role::getId).collect(Collectors.toList());
        userDTO.setRoleNameList(roleNameList);
        userDTO.setRoleIdList(roleIdList);

        return userDTO;
    }

}
