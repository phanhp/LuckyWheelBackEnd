package backend.gamification.entity.user;

import backend.gamification.entity.EntityNameModel;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person extends EntityNameModel {
    @Column(name = "dob")
    private LocalDate dob;
}
