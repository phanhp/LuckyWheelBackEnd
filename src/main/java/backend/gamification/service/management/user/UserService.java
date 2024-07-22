package backend.gamification.service.management.user;

import backend.gamification.dto.user.UserDTO;
import backend.gamification.entity.user.User;
import backend.gamification.service.RepositoryCaller;
import backend.gamification.service.utils.convert.DTOConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.*;

@Service
public class UserService {
    @Autowired
    RepositoryCaller repositoryCaller;
    @Autowired
    DTOConversion dtoConversion;



    //------------------------------------------------------------------------------------------------------------------
    //BASE USER SERVICE
    //Password service
    public String encodePassword(String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(password);
    }

    public boolean isPasswordMatch(String rawPassword, String encodedPassword) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
    }
    //------------------------------------------------------------------------------------------------------------------

    //GET USER
    public UserDTO getUserByUsername(String username){
        User user = repositoryCaller.getUserRepository().findUserByUsername(username).orElse(new User());
        return dtoConversion.convertUserToUserDTO(user);
    }
}
