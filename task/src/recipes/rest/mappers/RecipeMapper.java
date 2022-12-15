package recipes.rest.mappers;

import org.springframework.stereotype.Component;
import recipes.domain.entity.Recipe;
import recipes.rest.dto.RecipeDTO;

import java.util.Arrays;

@Component
public class RecipeMapper {

    public RecipeDTO toRecipeDTO(final Recipe recipe) {
        return RecipeDTO.builder()
                .name(recipe.getName())
                .description(recipe.getDescription())
                .category(recipe.getCategory())
                .date(recipe.getDate())
                .directions(Arrays.stream(recipe.getDirections()).toArray(String[]::new))
                .ingredients(Arrays.stream(recipe.getIngredients()).toArray(String[]::new))
                .build();
    }
}
