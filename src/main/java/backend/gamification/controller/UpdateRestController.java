package backend.gamification.controller;

import backend.gamification.dto.luckywheel.*;
import backend.gamification.dto.luckywheel.wheel.WheelColorParamsDTO;
import backend.gamification.dto.luckywheel.wheel.WheelImageParamsDTO;
import backend.gamification.dto.luckywheel.wheel.WheelTextParamsDTO;
import backend.gamification.entity.luckywheel.Reward;
import backend.gamification.entity.luckywheel.wheel.WheelColorParams;
import backend.gamification.exception.ObjectNotFoundException;
import backend.gamification.service.RepositoryCaller;
import backend.gamification.service.services.LuckyWheelUpdateService;
import backend.gamification.service.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UpdateRestController {
    @Autowired
    Utils utils;
    @Autowired
    RepositoryCaller repositoryCaller;
    @Autowired
    LuckyWheelUpdateService luckyWheelUpdateService;

    //PIXI_APP
    @PutMapping("/updatePixiApp/{pixiAppId}")
    public ResponseEntity<?> updatePixiApp(@PathVariable("pixiAppId") long pixiAppId,
                                           @RequestBody PixiAppDTO pixiAppDTO) {
        try {
            luckyWheelUpdateService.updatePixiApp(pixiAppId, pixiAppDTO);
            return ResponseEntity.ok("PixiApp update successfully");
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    //REWARD
    @PutMapping("/updateReward/{rewardId}")
    public ResponseEntity<?> updateReward(@PathVariable("rewardId") long rewardId,
                                          @RequestBody RewardDTO rewardDTO) {
        try {
            luckyWheelUpdateService.updateReward(rewardId, rewardDTO);
            return ResponseEntity.ok("Reward update successfully");
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    //BUTTON
    @PutMapping("/updateButton/{pixiAppId}")
    public ResponseEntity<?> updateButton(@PathVariable("pixiAppId") long pixiAppId,
                                          @RequestBody ButtonDTO buttonDTO) {

        try {
            luckyWheelUpdateService.updateButton(pixiAppId, buttonDTO);
            return ResponseEntity.ok("Button update successfully");
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    //POINTER
    @PutMapping("/updatePointer/{pixiAppId}")
    public ResponseEntity<?> updatePointer(@PathVariable("pixiAppId") long pixiAppId,
                                           @RequestBody PointerDTO pointerDTO) {
        try {
            luckyWheelUpdateService.updatePointer(pixiAppId, pointerDTO);
            return ResponseEntity.ok("Pointer update successfully");
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    //WHEEL
    @PutMapping("/updateWheel/{pixiAppId}")
    public ResponseEntity<?> updateWheel(@PathVariable("pixiAppId") long pixiAppId,
                                         @RequestBody WheelDTO wheelDTO) {
        try {
            luckyWheelUpdateService.updateWheel(pixiAppId, wheelDTO);
            return ResponseEntity.ok("Wheel update successfully");
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    //WHEEL_IMAGE_PARAMS
    @PutMapping("/updateWheelImageParams/{pixiAppId}")
    public ResponseEntity<?> updateWheelImageParams(@PathVariable("pixiAppId") long pixiAppId,
                                                    @RequestBody WheelImageParamsDTO wheelImageParamsDTO) {
        luckyWheelUpdateService.updateWheelImageParams(pixiAppId, wheelImageParamsDTO);
        return ResponseEntity.ok("WheelImageParams update successfully");
    }

    //WHEEL_TEXT_PARAMS
    @PutMapping("/updateWheelTextParams/{pixiAppId}")
    public ResponseEntity<?> updateWheelTextParams(@PathVariable("pixiAppId") long pixiAppId,
                                                   @RequestBody WheelTextParamsDTO wheelTextParamsDTO) {
        luckyWheelUpdateService.updateWheelTextParams(pixiAppId, wheelTextParamsDTO);
        return ResponseEntity.ok("WheelTextParams update successfully");
    }

    //WHEEL_COLOR_PARAMS
    @PutMapping("/updateWheelColorParamsList/{pixiAppId}")
    public ResponseEntity<?> updateWheelColorParamsList(@PathVariable("pixiAppId") long pixiAppId,
                                                        @RequestBody List<WheelColorParamsDTO> wheelColorParamsDTOList) {
        try {
            luckyWheelUpdateService.updateWheelColorParamsList(pixiAppId, wheelColorParamsDTOList);
            return ResponseEntity.ok("WheelColorParams list updated successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating WheelColorParams list");
        }
    }
}
