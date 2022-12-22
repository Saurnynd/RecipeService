package recipes.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id")
    private long recipeId;

    private String name;

    private String category;

    @CreationTimestamp
    private LocalDateTime date;

    private String description;

    private String[] ingredients;

    private String[] directions;

    @CreatedBy
    @ManyToOne
    @JoinColumn(name = "email")
    private Chef chef;
}
