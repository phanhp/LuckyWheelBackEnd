package backend.gamification.service.management;

import backend.gamification.dto.luckywheel.RewardDTO;
import backend.gamification.entity.luckywheel.Reward;
import backend.gamification.service.RepositoryCaller;
import backend.gamification.service.utils.Utils;
import backend.gamification.service.utils.convert.DTOConversion;
import backend.gamification.service.utils.convert.EntityConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class RewardService {
    @Autowired
    RepositoryCaller repositoryCaller;
    @Autowired
    Utils utils;
    @Autowired
    DTOConversion dtoConversion;
    @Autowired
    EntityConversion entityConversion;

    public void rewardAddOrUpdate(Reward reward) {
        repositoryCaller.getRewardRepository().save(reward);
    }

    public void rewardDelete(Reward reward) {
        repositoryCaller.getRewardRepository().delete(reward);
    }

    public void rewardDelete(long rewardId) {
        repositoryCaller.getRewardRepository().deleteById(rewardId);
    }

    public void rewardAddNew(String rewardName, int amount, double percentage, String description, MultipartFile imageFile) {
        String image = utils.convertMultipartFileToBase64(imageFile);
        Reward reward = new Reward(rewardName, amount, percentage, description, image);
        rewardAddOrUpdate(reward);
    }

    public void rewardAddNew(RewardDTO rewardDTO) {
        Reward reward = entityConversion.convertRewardDTOToReward(rewardDTO);
        rewardAddOrUpdate(reward);
    }

    public void rewardAddNew(RewardDTO rewardDTO, MultipartFile imageFile){
        String image = utils.convertMultipartFileToBase64(imageFile);
        rewardDTO.setImage(image);
        rewardAddNew(rewardDTO);
    }

    public void rewardUpdate(RewardDTO rewardDTO){
        Reward reward = entityConversion.convertRewardDTOToReward(rewardDTO);
        rewardAddOrUpdate(reward);
    }
}
