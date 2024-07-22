package backend.gamification.service;

import backend.gamification.repository.luckywheel.*;
import backend.gamification.repository.luckywheel.wheel.WheelColorParamsRepository;
import backend.gamification.repository.luckywheel.wheel.WheelImageParamsRepository;
import backend.gamification.repository.luckywheel.wheel.WheelTextParamsRepository;
import backend.gamification.repository.user.RoleRepository;
import backend.gamification.repository.user.UserRepository;
import backend.gamification.repository.user.UserRoleRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RepositoryCaller {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private PixiAppRepository pixiAppRepository;

    @Autowired
    private RewardRepository rewardRepository;
    @Autowired
    private WheelRepository wheelRepository;
    @Autowired
    private PointerRepository pointerRepository;
    @Autowired
    private ButtonRepository buttonRepository;
    @Autowired
    private WheelColorParamsRepository wheelColorParamsRepository;
    @Autowired
    private WheelImageParamsRepository wheelImageParamsRepository;
    @Autowired
    private WheelTextParamsRepository wheelTextParamsRepository;
}
