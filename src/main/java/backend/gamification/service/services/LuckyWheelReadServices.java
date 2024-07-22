package backend.gamification.service.services;

import backend.gamification.dto.luckywheel.*;
import backend.gamification.dto.luckywheel.wheel.WheelColorParamsDTO;
import backend.gamification.dto.luckywheel.wheel.WheelImageParamsDTO;
import backend.gamification.dto.luckywheel.wheel.WheelTextParamsDTO;
import backend.gamification.entity.luckywheel.*;
import backend.gamification.entity.luckywheel.wheel.WheelColorParams;
import backend.gamification.entity.luckywheel.wheel.WheelImageParams;
import backend.gamification.entity.luckywheel.wheel.WheelTextParams;
import backend.gamification.service.RepositoryCaller;
import backend.gamification.service.utils.Utils;
import backend.gamification.service.utils.convert.DTOConversion;
import backend.gamification.service.utils.convert.EntityConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LuckyWheelReadServices {
    @Autowired
    RepositoryCaller repositoryCaller;
    @Autowired
    DTOConversion dtoConversion;
    @Autowired
    EntityConversion entityConversion;
    @Autowired
    Utils utils;

    //PIXI_APP
    public PixiAppDTO findPixiAppDTOById(long pixiAppId) {
        PixiApp pixiApp = repositoryCaller.getPixiAppRepository().findPixiAppById(pixiAppId).orElse(new PixiApp());
        return dtoConversion.convertPixiAppToPixiAppDTO(pixiApp);
    }

    //REWARD
    public List<RewardDTO> findRewardDTOListByPixiAppId(long pixiAppId) {
        List<Reward> rewardList = repositoryCaller.getRewardRepository().findRewardListByPixiAppId(pixiAppId).orElse(new ArrayList<>());
        return dtoConversion.convertRewardListToRewardDTOList(rewardList);
    }

    //WHEEL
    public List<WheelDTO> findWheelDTOListByPixiAppId(long pixiAppId) {
        List<Wheel> wheelList = repositoryCaller.getWheelRepository().findWheelListByPixiAppId(pixiAppId).orElse(new ArrayList<>());
        return dtoConversion.convertWheelListToWheelDTOList(wheelList);
    }

    public WheelDTO findWheelDTOByPixiAppId(long pixiAppId) {
        List<WheelDTO> wheelDTOList = findWheelDTOListByPixiAppId(pixiAppId);
        if (!wheelDTOList.isEmpty()) {
            return wheelDTOList.get(wheelDTOList.size() - 1);
        } else {
            return dtoConversion.convertWheelToWheelDTO(new Wheel());
        }
    }

    //POINTER
    public List<PointerDTO> findPointerDTOListByPixiAppId(long pixiAppId) {
        List<Pointer> pointerList = repositoryCaller.getPointerRepository().findPointerListByPixiAppId(pixiAppId).orElse(new ArrayList<>());
        return dtoConversion.convertPointerListToPointerDTOList(pointerList);
    }

    public PointerDTO findPointerDTOByPixiAppId(long pixiAppId) {
        List<PointerDTO> pointerDTOList = findPointerDTOListByPixiAppId(pixiAppId);
        if (!pointerDTOList.isEmpty()) {
            return pointerDTOList.get(pointerDTOList.size() - 1);
        } else {
            return dtoConversion.convertPointerToPointerDTO(new Pointer());
        }
    }

    //BUTTON
    public List<ButtonDTO> findButtonDTOListByPixiAppId(long pixiAppId) {
        List<Button> buttonList = repositoryCaller.getButtonRepository().findButtonListByPixiAppId(pixiAppId).orElse(new ArrayList<>());
        return dtoConversion.convertButtonListToButtonDTOList(buttonList);
    }

    public ButtonDTO findButtonDTOByPixiAppId(long pixiAppId) {
        List<ButtonDTO> buttonDTOList = findButtonDTOListByPixiAppId(pixiAppId);
        if (!buttonDTOList.isEmpty()) {
            return buttonDTOList.get(buttonDTOList.size() - 1);
        } else {
            return dtoConversion.convertButtonToButtonDTO(new Button());
        }
    }

    //WHEEL_COLOR_PARAMS
    public List<WheelColorParamsDTO> findWheelColorParamsDTOListByPixiAppId(long pixiAppId) {
        List<WheelColorParams> wheelColorParamsList = repositoryCaller.getWheelColorParamsRepository().findWheelColorParamsListByPixiAppId(pixiAppId).orElse(new ArrayList<>());
        return dtoConversion.convertWheelColorParamsListToWheelColorParamsDTOList(wheelColorParamsList);
    }

    //WHEEL_IMAGE_PARAMS
    public List<WheelImageParamsDTO> findWheelImageParamsDTOListByPixiAppId(long pixiAppId) {
        List<WheelImageParams> wheelImageParamsList = repositoryCaller.getWheelImageParamsRepository().findWheelImageParamsListByPixiAppId(pixiAppId).orElse(new ArrayList<>());
        return dtoConversion.convertWheelImageParamsListToWheelImageParamsDTOList(wheelImageParamsList);
    }

    public WheelImageParamsDTO findWheelImageParamsDTOByPixiAppId(long pixiAppId) {
        List<WheelImageParamsDTO> wheelImageParamsDTOList = findWheelImageParamsDTOListByPixiAppId(pixiAppId);
        if (!wheelImageParamsDTOList.isEmpty()) {
            return wheelImageParamsDTOList.get(wheelImageParamsDTOList.size() - 1);
        } else {
            return dtoConversion.convertWheelImageParamsToWheelImageParamsDTO(new WheelImageParams());
        }
    }

    //WHEEL_TEXT_PARAMS
    public List<WheelTextParamsDTO> findWheelTextParamsDTOListByPixiAppId(long pixiAppId) {
        List<WheelTextParams> wheelTextParamsList = repositoryCaller.getWheelTextParamsRepository().findWheelTextParamsListByPixiAppId(pixiAppId).orElse(new ArrayList<>());
        return dtoConversion.convertWheelTextParamsListToWheelTextParamsDTOList(wheelTextParamsList);
    }

    public WheelTextParamsDTO findWheelTextParamsDTOByPixiAppId(long pixiAppId) {
        List<WheelTextParamsDTO> wheelTextParamsDTOList = findWheelTextParamsDTOListByPixiAppId(pixiAppId);
        if (!wheelTextParamsDTOList.isEmpty()) {
            return wheelTextParamsDTOList.get(wheelTextParamsDTOList.size() - 1);
        } else {
            return dtoConversion.convertWheelTextParamsToWheelTextParamsDTO(new WheelTextParams());
        }
    }

}
