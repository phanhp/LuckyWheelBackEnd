package backend.gamification.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntityNameModel extends EntityModel{
    @Column(name = "name")
    private String name;

    public EntityNameModel(long id, String name) {
        super(id);
        this.name = name;
    }
}
