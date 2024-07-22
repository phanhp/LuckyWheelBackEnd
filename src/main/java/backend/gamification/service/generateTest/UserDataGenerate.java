package backend.gamification.service.generateTest;

import backend.gamification.entity.user.Role;
import backend.gamification.entity.user.User;
import backend.gamification.entity.user.UserRole;
import backend.gamification.service.RepositoryCaller;
import backend.gamification.service.management.user.RoleService;
import backend.gamification.service.management.user.UserService;
import backend.gamification.service.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class UserDataGenerate {
    @Autowired
    Utils utils;
    @Autowired
    RepositoryCaller repositoryCaller;
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    //Tạo User để Test
    //3 Master, 8 Admin, 30 Manager, 120 Client, 800 Player
    public void generateUser(){
        //Master
        for (int i=1; i<=3; i++){
            User user = new User();
            user.setUsername("Master_"+i);
            user.setPassword(userService.encodePassword("123"));
            user.setName("master_"+i+"_full_name");
            user.setActive(true);
            user.setAvailable(true);
            repositoryCaller.getUserRepository().save(user);
            List<Role> roleList = roleService.findRoleWithStringList("MASTER, USER, PLAYER");
            for (int j=0; j<roleList.size(); j++){
                UserRole userRole = new UserRole(user, roleList.get(j));
                repositoryCaller.getUserRoleRepository().save(userRole);
            }
        }

        //Admin
        for (int i=1; i<=8; i++){
            User user = new User();
            user.setUsername("Admin_"+i);
            user.setPassword(userService.encodePassword("123"));
            user.setName("admin_"+i+"_full_name");
            user.setActive(true);
            user.setAvailable(true);
            repositoryCaller.getUserRepository().save(user);
            List<Role> roleList = roleService.findRoleWithStringList("ADMIN, USER, PLAYER");
            for (int j=0; j<roleList.size(); j++){
                UserRole userRole = new UserRole(user, roleList.get(j));
                repositoryCaller.getUserRoleRepository().save(userRole);
            }
        }

        //Manager
        for (int i=1; i<=30; i++){
            User user = new User();
            user.setUsername("Manager_"+i);
            user.setPassword(userService.encodePassword("123"));
            user.setName("manager_"+i+"_full_name");
            LocalDate dob = LocalDate.of(1980,01,01);
            user.setActive(true);
            user.setAvailable(true);
            repositoryCaller.getUserRepository().save(user);
            List<Role> roleList = roleService.findRoleWithStringList("MANAGER, USER, PLAYER");
            for (int j=0; j<roleList.size(); j++){
                UserRole userRole = new UserRole(user, roleList.get(j));
                repositoryCaller.getUserRoleRepository().save(userRole);
            }
        }

        //Client
        for (int i=1; i<=120; i++){
            User user = new User();
            user.setUsername("Client_"+i);
            user.setPassword(userService.encodePassword("123"));
            user.setName("client_"+i+"_full_name");
            LocalDate dob = LocalDate.of(1980,01,01);
            user.setActive(true);
            user.setAvailable(true);
            repositoryCaller.getUserRepository().save(user);
            List<Role> roleList = roleService.findRoleWithStringList("CLIENT, USER, PLAYER");
            for (int j=0; j<roleList.size(); j++){
                UserRole userRole = new UserRole(user, roleList.get(j));
                repositoryCaller.getUserRoleRepository().save(userRole);
            }
        }

        //Player
        for (int i=1; i<=800; i++){
            User user = new User();
            user.setUsername("Player_"+i);
            user.setPassword(userService.encodePassword("123"));
            user.setName("player_"+i+"_full_name");
            LocalDate dob = LocalDate.of(1980,01,01);
            user.setActive(true);
            user.setAvailable(true);
            repositoryCaller.getUserRepository().save(user);
            List<Role> roleList = roleService.findRoleWithStringList("USER, PLAYER");
            for (int j=0; j<roleList.size(); j++){
                UserRole userRole = new UserRole(user, roleList.get(j));
                repositoryCaller.getUserRoleRepository().save(userRole);
            }
        }
    }
}
