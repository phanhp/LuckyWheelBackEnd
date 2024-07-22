package backend.gamification.service.services;

import backend.gamification.dto.luckywheel.*;
import backend.gamification.dto.luckywheel.wheel.WheelColorParamsDTO;
import backend.gamification.dto.luckywheel.wheel.WheelImageParamsDTO;
import backend.gamification.dto.luckywheel.wheel.WheelTextParamsDTO;
import backend.gamification.entity.luckywheel.*;
import backend.gamification.entity.luckywheel.wheel.WheelColorParams;
import backend.gamification.entity.luckywheel.wheel.WheelImageParams;
import backend.gamification.entity.luckywheel.wheel.WheelTextParams;
import backend.gamification.exception.ObjectNotFoundException;
import backend.gamification.service.RepositoryCaller;
import backend.gamification.service.utils.Utils;
import backend.gamification.service.utils.convert.DTOConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LuckyWheelUpdateService {
    @Autowired
    Utils utils;
    @Autowired
    RepositoryCaller repositoryCaller;
    @Autowired
    DTOConversion dtoConversion;
    @Autowired
    LuckyWheelReadServices luckyWheelReadServices;

    //PIXI_APP
    public void updatePixiApp(long pixiAppId, PixiAppDTO pixiAppDTO) throws ObjectNotFoundException {
        PixiApp pixiApp = repositoryCaller.getPixiAppRepository().findPixiAppById(pixiAppId).orElse(null);
        if (pixiApp == null) {
            throw new ObjectNotFoundException("Can not find pixi app");
        }
        pixiApp.setWidthRate(pixiAppDTO.getWidthRate());
        pixiApp.setHeightRate(pixiAppDTO.getHeightRate());
        pixiApp.setColor(pixiAppDTO.getColor());
        pixiApp.setColorAlpha(pixiAppDTO.getColorAlpha());

        pixiApp.setPcBackgroundImageUsed(pixiAppDTO.isPcBackgroundImageUsed());
        pixiApp.setPcBackgroundSource(pixiAppDTO.getPcBackgroundSource());
        pixiApp.setPcBackgroundWidthRate(pixiAppDTO.getPcBackgroundWidthRate());
        pixiApp.setPcBackgroundHeightRate(pixiAppDTO.getPcBackgroundHeightRate());
        pixiApp.setPcBackgroundBonusWidth(pixiAppDTO.getPcBackgroundBonusWidth());
        pixiApp.setPcBackgroundBonusHeight(pixiAppDTO.getPcBackgroundBonusHeight());
        pixiApp.setPcBackgroundX(pixiAppDTO.getPcBackgroundX());
        pixiApp.setPcBackgroundY(pixiAppDTO.getPcBackgroundY());

        pixiApp.setMobileBackgroundImageUsed(pixiAppDTO.isMobileBackgroundImageUsed());
        pixiApp.setMobileBackgroundSource(pixiAppDTO.getMobileBackgroundSource());
        pixiApp.setMobileBackgroundWidthRate(pixiAppDTO.getMobileBackgroundWidthRate());
        pixiApp.setMobileBackgroundHeightRate(pixiAppDTO.getMobileBackgroundHeightRate());
        pixiApp.setMobileBackgroundBonusWidth(pixiAppDTO.getMobileBackgroundBonusWidth());
        pixiApp.setMobileBackgroundBonusHeight(pixiAppDTO.getMobileBackgroundBonusHeight());
        pixiApp.setMobileBackgroundX(pixiAppDTO.getMobileBackgroundX());
        pixiApp.setMobileBackgroundY(pixiAppDTO.getMobileBackgroundY());

        repositoryCaller.getPixiAppRepository().save(pixiApp);
    }

    //REWARD
    public void updateReward(long rewardId, RewardDTO rewardDTO) throws ObjectNotFoundException {
        Reward reward = repositoryCaller.getRewardRepository().findRewardById(rewardId).orElse(null);
        if (reward != null) {
            reward.setName(rewardDTO.getName());
            reward.setAmount(rewardDTO.getAmount());
            reward.setEase(rewardDTO.getEase());
            reward.setLimitType(reward.getLimitType());
            reward.setImage(rewardDTO.getImage());

            repositoryCaller.getRewardRepository().save(reward);
        } else {
            throw new ObjectNotFoundException("Can not find reward");
        }
    }

    //BUTTON
    public void updateButton(long pixiAppId, ButtonDTO buttonDTO) throws ObjectNotFoundException {
        List<Button> buttonList = repositoryCaller.getButtonRepository().findButtonListByPixiAppId(pixiAppId).orElse(new ArrayList<>());
        Button button;
        if (!buttonList.isEmpty()) {
            button = buttonList.get(buttonList.size() - 1);
        } else {
            button = new Button();
            PixiApp pixiApp = repositoryCaller.getPixiAppRepository().findPixiAppById(pixiAppId).orElse(null);
            if (pixiApp != null) {
                button.setPixiApp(pixiApp);
            } else {
                throw new ObjectNotFoundException("Can not find pixi app");
            }
        }

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
        button.setHoverBackgroundColor(buttonDTO.getHoverBackgroundColor());
        button.setHoverWidth(buttonDTO.getHoverWidth());
        button.setHoverHeight(buttonDTO.getHoverHeight());
        button.setHoverRoundUp(buttonDTO.getHoverRoundUp());
        button.setHoverX(buttonDTO.getHoverX());
        button.setHoverY(buttonDTO.getHoverY());

        repositoryCaller.getButtonRepository().save(button);
    }

    //POINTER
    public void updatePointer(long pixiAppId, PointerDTO pointerDTO) throws ObjectNotFoundException {
        List<Pointer> pointerList = repositoryCaller.getPointerRepository().findPointerListByPixiAppId(pixiAppId).orElse(new ArrayList<>());
        Pointer pointer;
        if (!pointerList.isEmpty()) {
            pointer = pointerList.get(pointerList.size() - 1);
        } else {
            pointer = new Pointer();
            PixiApp pixiApp = repositoryCaller.getPixiAppRepository().findPixiAppById(pixiAppId).orElse(null);
            if (pixiApp != null) {
                pointer.setPixiApp(pixiApp);
            } else {
                throw new ObjectNotFoundException("Can not find pixi app");
            }
        }

        pointer.setPointerSource(pointerDTO.getPointerSource());
        pointer.setWidthRate(pointerDTO.getWidthRate());
        pointer.setHeightRate(pointerDTO.getHeightRate());
        pointer.setBonusWidth(pointerDTO.getBonusWidth());
        pointer.setBonusHeight(pointerDTO.getBonusHeight());
        pointer.setPointerX(pointerDTO.getPointerX());
        pointer.setPointerY(pointerDTO.getPointerY());

        repositoryCaller.getPointerRepository().save(pointer);
    }

    //WHEEL
    public void updateWheel(long pixiAppId, WheelDTO wheelDTO) throws ObjectNotFoundException {
        List<Wheel> wheelList = repositoryCaller.getWheelRepository().findWheelListByPixiAppId(pixiAppId).orElse(new ArrayList<>());
        Wheel wheel;
        if (!wheelList.isEmpty()) {
            wheel = wheelList.get(wheelList.size() - 1);
        } else {
            wheel = new Wheel();
            PixiApp pixiApp = repositoryCaller.getPixiAppRepository().findPixiAppById(pixiAppId).orElse(null);
            if (pixiApp != null) {
                wheel.setPixiApp(pixiApp);
            } else {
                throw new ObjectNotFoundException("Can not find pixi app");
            }
        }

        wheel.setContainerX(wheelDTO.getContainerX());
        wheel.setContainerY(wheelDTO.getContainerY());

        wheel.setWheelRadiusRate(wheelDTO.getWheelRadiusRate());
        wheel.setWheelRadiusBonus(wheelDTO.getWheelRadiusBonus());
        wheel.setWheelRadiusFixed(wheelDTO.isWheelRadiusFixed());

        wheel.setBackgroundUsed(wheelDTO.isBackgroundUsed());
        wheel.setBackgroundSource(wheelDTO.getBackgroundSource());
        wheel.setBackgroundWidthRate(wheelDTO.getBackgroundWidthRate());
        wheel.setBackgroundHeightRate(wheelDTO.getBackgroundHeightRate());
        wheel.setBackgroundBonusWidth(wheelDTO.getBackgroundBonusWidth());
        wheel.setBackgroundBonusHeight(wheelDTO.getBackgroundBonusHeight());
        wheel.setBackgroundWidthFixed(wheelDTO.getBackgroundWidthFixed());
        wheel.setBackgroundHeightFixed(wheelDTO.getBackgroundHeightFixed());

        repositoryCaller.getWheelRepository().save(wheel);
    }

    //WHEEL_COLOR_PARAMS
    public void updateWheelColorParamsList(long pixiAppId, List<WheelColorParamsDTO> wheelColorParamsDTOList) {
        try {
            List<Wheel> wheelList = repositoryCaller.getWheelRepository().findWheelListByPixiAppId(pixiAppId).orElse(new ArrayList<>());
            Wheel wheel;
            if (!wheelList.isEmpty()) {
                wheel = wheelList.get(wheelList.size() - 1);
            } else {
                wheel = new Wheel();
                repositoryCaller.getWheelRepository().save(wheel);
            }

            List<WheelColorParams> wheelColorParamsList = repositoryCaller.getWheelColorParamsRepository()
                    .findWheelColorParamsListByPixiAppId(pixiAppId)
                    .orElse(new ArrayList<>());

            List<Long> updatedId = wheelColorParamsDTOList.stream()
                    .map(WheelColorParamsDTO::getId)
                    .collect(Collectors.toList());

            List<WheelColorParams> deleteList = wheelColorParamsList.stream()
                    .filter(wheelColorParams -> !updatedId.contains(wheelColorParams.getId()))
                    .collect(Collectors.toList());
            repositoryCaller.getWheelColorParamsRepository().deleteAll(deleteList);

            for (WheelColorParamsDTO wheelColorParamsDTO : wheelColorParamsDTOList) {
                WheelColorParams wheelColorParams = wheelColorParamsList.stream()
                        .filter(wcp -> wcp.getId() == (wheelColorParamsDTO.getId()))
                        .findFirst()
                        .orElse(new WheelColorParams());

                wheelColorParams.setColorData(wheelColorParamsDTO.getColorData());
                wheelColorParams.setColorAlpha(wheelColorParamsDTO.getColorAlpha());

                wheelColorParams.setWheel(wheel);

                repositoryCaller.getWheelColorParamsRepository().save(wheelColorParams);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //WHEEL_IMAGE_PARAMS
    public void updateWheelImageParams(long pixiAppId, WheelImageParamsDTO wheelImageParamsDTO) {
        List<WheelImageParams> wheelImageParamsList = repositoryCaller.getWheelImageParamsRepository().findWheelImageParamsListByPixiAppId(pixiAppId).orElse(new ArrayList<>());
        WheelImageParams wheelImageParams;
        if (!wheelImageParamsList.isEmpty()) {
            wheelImageParams = wheelImageParamsList.get(wheelImageParamsList.size() - 1);
        } else {
            wheelImageParams = new WheelImageParams();
            List<Wheel> wheelList = repositoryCaller.getWheelRepository().findWheelListByPixiAppId(pixiAppId).orElse(new ArrayList<>());
            if (!wheelList.isEmpty()) {
                Wheel wheel = wheelList.get(wheelList.size() - 1);
                wheelImageParams.setWheel(wheel);
            } else {
                Wheel wheel = new Wheel();
                repositoryCaller.getWheelRepository().save(wheel);
                wheelImageParams.setWheel(wheel);
            }
        }

        wheelImageParams.setProperty(wheelImageParamsDTO.getProperty());
        wheelImageParams.setUsed(wheelImageParamsDTO.isUsed());
        wheelImageParams.setWidthRate(wheelImageParamsDTO.getWidthRate());
        wheelImageParams.setHeightRate(wheelImageParamsDTO.getHeightRate());
        wheelImageParams.setBonusWidth(wheelImageParamsDTO.getBonusWidth());
        wheelImageParams.setBonusHeight(wheelImageParamsDTO.getBonusHeight());
        wheelImageParams.setPercentageDistance(wheelImageParamsDTO.getPercentageDistance());
        wheelImageParams.setBonusDistance(wheelImageParamsDTO.getBonusDistance());
        wheelImageParams.setX(wheelImageParamsDTO.getX());
        wheelImageParams.setY(wheelImageParamsDTO.getY());
        wheelImageParams.setRotateFixed(wheelImageParamsDTO.isRotateFixed());
        wheelImageParams.setRotateAngle(wheelImageParamsDTO.getRotateAngle());

        repositoryCaller.getWheelImageParamsRepository().save(wheelImageParams);
    }

    //WHEEL_TEXT_PARAMS
    public void updateWheelTextParams(long pixiAppId, WheelTextParamsDTO wheelTextParamsDTO) {
        List<WheelTextParams> wheelTextParamsList = repositoryCaller.getWheelTextParamsRepository().findWheelTextParamsListByPixiAppId(pixiAppId).orElse(new ArrayList<>());
        WheelTextParams wheelTextParams;
        if (!wheelTextParamsList.isEmpty()) {
            wheelTextParams = wheelTextParamsList.get(wheelTextParamsList.size() - 1);
        } else {
            wheelTextParams = new WheelTextParams();
            List<Wheel> wheelList = repositoryCaller.getWheelRepository().findWheelListByPixiAppId(pixiAppId).orElse(new ArrayList<>());
            if (!wheelList.isEmpty()) {
                Wheel wheel = wheelList.get(wheelList.size() - 1);
                wheelTextParams.setWheel(wheel);
            } else {
                Wheel wheel = new Wheel();
                repositoryCaller.getWheelRepository().save(wheel);
                wheelTextParams.setWheel(wheel);
            }
        }

        wheelTextParams.setProperty(wheelTextParamsDTO.getProperty());
        wheelTextParams.setUsed(wheelTextParamsDTO.isUsed());
        wheelTextParams.setFontFamily(wheelTextParamsDTO.getFontFamily());
        wheelTextParams.setColor(wheelTextParamsDTO.getColor());
        wheelTextParams.setFontSize(wheelTextParamsDTO.getFontSize());
        wheelTextParams.setRotateFixed(wheelTextParamsDTO.isRotateFixed());
        wheelTextParams.setRotate(wheelTextParamsDTO.getRotate());
        wheelTextParams.setPercentageDistance(wheelTextParamsDTO.getPercentageDistance());
        wheelTextParams.setBonusDistance(wheelTextParamsDTO.getBonusDistance());
        wheelTextParams.setTextX(wheelTextParamsDTO.getTextX());
        wheelTextParams.setTextY(wheelTextParamsDTO.getTextY());

        repositoryCaller.getWheelTextParamsRepository().save(wheelTextParams);
    }

}
