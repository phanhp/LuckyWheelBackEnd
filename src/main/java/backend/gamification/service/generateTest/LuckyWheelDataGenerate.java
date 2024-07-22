package backend.gamification.service.generateTest;

import backend.gamification.entity.luckywheel.*;
import backend.gamification.entity.luckywheel.wheel.WheelColorParams;
import backend.gamification.entity.luckywheel.wheel.WheelImageParams;
import backend.gamification.entity.luckywheel.wheel.WheelTextParams;
import backend.gamification.service.RepositoryCaller;
import backend.gamification.service.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LuckyWheelDataGenerate {
    @Autowired
    RepositoryCaller repositoryCaller;
    @Autowired
    Utils utils;
    public void generateWheel(){
        PixiApp pixiApp = new PixiApp();

        repositoryCaller.getPixiAppRepository().save(pixiApp);

        Reward babLuck = new Reward("Bad Luck",0, 20, "unlimited", "https://png.pngtree.com/png-clipart/20231119/original/pngtree-bad-luck-unlucky-photo-png-image_13643990.png");
        babLuck.setPixiApp(pixiApp);
        repositoryCaller.getRewardRepository().save(babLuck);
        for (int i=0; i<9; i++){
            Reward reward = new Reward("Reward "+(i+1),14+(i*2),10+i,"limited","https://static.vecteezy.com/system/resources/thumbnails/020/696/324/small_2x/3d-minimal-gift-box-award-event-rewards-bonus-gift-box-gift-box-with-a-pile-of-money-3d-illustration-png.png");
            reward.setPixiApp(pixiApp);
            repositoryCaller.getRewardRepository().save(reward);
        }

        Pointer pointer = new Pointer();
        pointer.setPixiApp(pixiApp);
        repositoryCaller.getPointerRepository().save(pointer);

        Button button = new Button();
        button.setPixiApp(pixiApp);
        repositoryCaller.getButtonRepository().save(button);

        Wheel wheel = new Wheel();
        wheel.setPixiApp(pixiApp);
        repositoryCaller.getWheelRepository().save(wheel);

        List<String> baseColorListData = utils.separateStringWithCommaToListString("#FF0000, #00FF00, #0000FF, #FFFF00, #FF00FF, #00FFFF, #FF8000, #8000FF, #00FF80, #FF0080");
        for (int i =0; i<baseColorListData.size(); i++){
            WheelColorParams wheelColorParams = new WheelColorParams();
            wheelColorParams.setColorData(baseColorListData.get(i));
            wheelColorParams.setWheel(wheel);
            repositoryCaller.getWheelColorParamsRepository().save(wheelColorParams);
        }

        WheelImageParams wheelImageParams = new WheelImageParams();
        wheelImageParams.setWheel(wheel);
        repositoryCaller.getWheelImageParamsRepository().save(wheelImageParams);

        WheelTextParams wheelTextParams = new WheelTextParams();
        wheelTextParams.setWheel(wheel);
        repositoryCaller.getWheelTextParamsRepository().save(wheelTextParams);
    }
}
