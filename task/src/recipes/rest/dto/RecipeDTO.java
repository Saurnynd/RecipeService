package recipes.rest.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class RecipeDTO {
    private String name;
    private String description;
    private String category;
    private LocalDateTime date;
    private String ingredients[];
    private String directions[];
}
