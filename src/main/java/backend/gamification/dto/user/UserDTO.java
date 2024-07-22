package backend.gamification.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private long id;
    private String username;
    private String password;
    private String name;
    private String dob;
    private String active;
    private String available;
    private List<String> roleNameList;
    private List<Long> roleIdList;
}
