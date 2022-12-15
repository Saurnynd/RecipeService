package recipes.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "chefs")
public class Chef {
    private static final String REGEX_PATTERN = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";

    @Id
    @Pattern(regexp = REGEX_PATTERN)
    @Column(name = "email")
    private String email;

    @Size(min = 8,max = 255)
    @NotBlank
    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "chef", cascade = CascadeType.ALL)
    private Set<Recipe> recipes;

}
