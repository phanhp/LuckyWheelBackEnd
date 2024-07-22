package backend.gamification.entity.user;

import backend.gamification.entity.EntityNameModel;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role extends EntityNameModel {
    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<UserRole> userRoleList;

    public Role(String name) {
        super(name);
    }
}
