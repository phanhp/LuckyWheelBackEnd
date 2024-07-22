package backend.gamification.service.management.user;

import backend.gamification.entity.user.Role;
import backend.gamification.service.RepositoryCaller;
import backend.gamification.service.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {
    @Autowired
    RepositoryCaller repositoryCaller;
    @Autowired
    Utils utils;

    public List<Role> findRoleWithStringList (String listOfRole){
        List<String> roles = utils.separateStringWithCommaToListString(listOfRole);
        List<Role> roleList = new ArrayList<>();
        for (int i=0; i<roles.size(); i++){
            Role role = repositoryCaller.getRoleRepository().findByName(roles.get(i)).orElse(null);
            if (role!= null){
                roleList.add(role);
            }
        }
        return roleList;
    }

    public List<Role> findRoleListByUserId(long userId){
        return repositoryCaller.getRoleRepository().findRoleListByUserId(userId).orElse(new ArrayList<>());
    }
}
