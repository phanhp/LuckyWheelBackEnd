package backend.gamification.controller;

import backend.gamification.service.generateTest.LuckyWheelDataGenerate;
import backend.gamification.service.generateTest.RewardDataGenerate;
import backend.gamification.service.generateTest.RoleDataGenerate;
import backend.gamification.service.generateTest.UserDataGenerate;
import backend.gamification.service.services.LuckyWheelUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TestController {
    //AUTOWIRED
    @Autowired
    UserDataGenerate userDataGenerate;
    @Autowired
    RoleDataGenerate roleDataGenerate;
    @Autowired
    LuckyWheelDataGenerate luckyWheelDataGenerate;
    @Autowired
    LuckyWheelUpdateService wheelUpdateService;
    //------------------------------------------------------------------------------------------------------------------

    @GetMapping(value = "/")
    public String dataGeneratePage(){

        return "index.html";
    }

    @GetMapping(value = "/data-generate/role-generate")
    public String roleDataGenerate(){
        roleDataGenerate.generateRole();
        return "redirect:/";
    }

    @GetMapping(value = "/data-generate/user-generate")
    public String userDataGenerate(){
        userDataGenerate.generateUser();
        return "redirect:/";
    }

    @GetMapping(value = "/data-generate/wheel-data-generate")
    public String wheelDataGenerate(){
        luckyWheelDataGenerate.generateWheel();
        return "redirect:/";
    }
}
