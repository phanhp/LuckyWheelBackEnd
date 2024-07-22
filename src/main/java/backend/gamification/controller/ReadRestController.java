package backend.gamification.controller;

import backend.gamification.dto.luckywheel.*;
import backend.gamification.dto.luckywheel.wheel.WheelColorParamsDTO;
import backend.gamification.dto.luckywheel.wheel.WheelImageParamsDTO;
import backend.gamification.dto.luckywheel.wheel.WheelTextParamsDTO;
import backend.gamification.entity.luckywheel.Reward;
import backend.gamification.entity.luckywheel.Wheel;
import backend.gamification.service.RepositoryCaller;
import backend.gamification.service.services.LuckyWheelReadServices;
import backend.gamification.service.utils.convert.DTOConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ReadRestController {
    @Autowired
    LuckyWheelReadServices luckyWheelReadServices;
    @Autowired
    RepositoryCaller repositoryCaller;
    @Autowired
    DTOConversion dtoConversion;
    private WheelDTO wheelDTO;


    //PIXI_APP
    @GetMapping(value = "/load-pixi-app")
    public PixiAppDTO loadPixiApp() {
        return luckyWheelReadServices.findPixiAppDTOById(1);
    }

    @GetMapping(value = "/loadPixiApp/{pixiAppId}")
    public PixiAppDTO loadPixiApp(@PathVariable("pixiAppId") long pixiAppId) {
        return luckyWheelReadServices.findPixiAppDTOById(pixiAppId);
    }

    //REWARD_LIST
    @GetMapping(value = "/load-reward-list")
    public List<RewardDTO> loadRewardList() {
        return luckyWheelReadServices.findRewardDTOListByPixiAppId(1);
    }

    @GetMapping(value = "/loadRewardList/{pixiAppId}")
    public List<RewardDTO> loadRewardList(@PathVariable("pixiAppId") long pixiAppId){
        System.out.println("IN");
        return luckyWheelReadServices.findRewardDTOListByPixiAppId(pixiAppId);
    }

    //POINTER_LIST
    @GetMapping(value = "/load-pointer-list")
    public List<PointerDTO> loadPointerList() {
        return luckyWheelReadServices.findPointerDTOListByPixiAppId(1);
    }

    @GetMapping(value = "/loadPointer/{pixiAppId}")
    public PointerDTO loadPointer(@PathVariable("pixiAppId") long pixiAppId){
        return luckyWheelReadServices.findPointerDTOByPixiAppId(pixiAppId);
    }

    //BUTTON_LIST
    @GetMapping(value = "/load-button-list")
    public List<ButtonDTO> loadButtonList() {
        return luckyWheelReadServices.findButtonDTOListByPixiAppId(1);
    }

    @GetMapping(value = "/loadButton/{pixiAppId}")
    public ButtonDTO loadButton (@PathVariable("pixiAppId") long pixiAppId){
        return luckyWheelReadServices.findButtonDTOByPixiAppId(pixiAppId);
    }

    //WHEEL
    @GetMapping(value = "/load-wheel-list")
    public List<WheelDTO> loadWheelList() {
        return luckyWheelReadServices.findWheelDTOListByPixiAppId(1);
    }

    @GetMapping(value = "/loadWheel/{pixiAppId}")
    public WheelDTO loadWheel(@PathVariable("pixiAppId") long pixiAppId){
        return luckyWheelReadServices.findWheelDTOByPixiAppId(pixiAppId);
    }

    //WHEEL_COLOR_PARAMS_LIST
    @GetMapping(value = "/load-wheel-color-params-list")
    public List<WheelColorParamsDTO> loadWheelColorParamsList() {
        return luckyWheelReadServices.findWheelColorParamsDTOListByPixiAppId(1);
    }

    @GetMapping(value = "/loadWheelColorParamsList/{pixiAppId}")
    public List<WheelColorParamsDTO> loadWheelColorParamsList(@PathVariable("pixiAppId") long pixiAppId) {
        return luckyWheelReadServices.findWheelColorParamsDTOListByPixiAppId(pixiAppId);
    }

    //WHEEL_IMAGE_PARAMS
    @GetMapping(value = "/load-wheel-image-params-list")
    public List<WheelImageParamsDTO> loadWheelImageParamsList() {
        return luckyWheelReadServices.findWheelImageParamsDTOListByPixiAppId(1);
    }

    @GetMapping(value = "/loadWheelImageParams/{pixiAppId}")
    public WheelImageParamsDTO loadWheelImageParams(@PathVariable("pixiAppId") long pixiAppId){
        return luckyWheelReadServices.findWheelImageParamsDTOByPixiAppId(pixiAppId);
    }

    //WHEEL_TEXT_PARAMS
    @GetMapping(value = "/load-wheel-text-params-list")
    public List<WheelTextParamsDTO> loadWheelTextParamsList() {
        return luckyWheelReadServices.findWheelTextParamsDTOListByPixiAppId(1);
    }

    @GetMapping(value = "/loadWheelTextParams/{pixiAppId}")
    public WheelTextParamsDTO loadWheelTextParams(@PathVariable("pixiAppId") long pixiAppId){
        return luckyWheelReadServices.findWheelTextParamsDTOByPixiAppId(pixiAppId);
    }
}
