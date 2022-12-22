package recipes.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "chefs")
@Builder
public class Chef {
    @Id
    private String email;

    @Size(min = 8, max = 255)
    private String password;

    @OneToMany(mappedBy = "chef", cascade = CascadeType.ALL)
    private Set<Recipe> recipes;

}
