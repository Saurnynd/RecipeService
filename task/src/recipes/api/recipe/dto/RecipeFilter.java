package recipes.api.recipe.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RecipeFilter {
    private long id;
}
