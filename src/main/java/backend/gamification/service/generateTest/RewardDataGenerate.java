package backend.gamification.service.generateTest;

import backend.gamification.entity.luckywheel.Reward;
import backend.gamification.repository.luckywheel.RewardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RewardDataGenerate {
    @Autowired
    RewardRepository rewardRepository;
    public void generateReward(){
        Reward babLuck = new Reward("Bad Luck",0, 20, "unlimited", "https://png.pngtree.com/png-clipart/20231119/original/pngtree-bad-luck-unlucky-photo-png-image_13643990.png");
        rewardRepository.save(babLuck);
        for (int i=0; i<9; i++){
            Reward reward = new Reward("Reward "+i,10,10+i,"limited","https://static.vecteezy.com/system/resources/thumbnails/020/696/324/small_2x/3d-minimal-gift-box-award-event-rewards-bonus-gift-box-gift-box-with-a-pile-of-money-3d-illustration-png.png");
            rewardRepository.save(reward);
        }
    }
}
