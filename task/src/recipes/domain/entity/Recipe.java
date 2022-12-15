package recipes.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id")
    private long recipeId;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "category")
    private String category;

    @CreationTimestamp
    @Column(name = "date")
    private LocalDateTime date = LocalDateTime.now();

    @NotBlank
    @Column(name = "description")
    private String description;

    @Column(name = "ingredients")
    @NotEmpty
    private String[] ingredients;

    @Column(name = "directions")
    @NotEmpty
    private String[] directions;

    @CreatedBy
    @ManyToOne
    @JoinColumn(name = "email")
    private Chef chef;
}
