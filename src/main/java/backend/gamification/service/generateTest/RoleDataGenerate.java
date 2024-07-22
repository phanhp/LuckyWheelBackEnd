package backend.gamification.service.generateTest;

import backend.gamification.entity.user.Role;
import backend.gamification.repository.user.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleDataGenerate {
    @Autowired
    RoleRepository roleRepository;

    public void generateRole(){
        Role master = new Role("MASTER");
        Role admin = new Role("ADMIN");
        Role manager = new Role("MANAGER");
        Role client = new Role("CLIENT");
        Role user = new Role("USER");
        Role player = new Role("PLAYER");
        if(!roleRepository.existsByName("MASTER")){
            roleRepository.save(master);
        }
        if(!roleRepository.existsByName("ADMIN")){
            roleRepository.save(admin);
        }
        if(!roleRepository.existsByName("MANAGER")){
            roleRepository.save(manager);
        }
        if(!roleRepository.existsByName("CLIENT")){
            roleRepository.save(client);
        }
        if(!roleRepository.existsByName("USER")){
            roleRepository.save(user);
        }
        if(!roleRepository.existsByName("PLAYER")){
            roleRepository.save(player);
        }
    }
}
