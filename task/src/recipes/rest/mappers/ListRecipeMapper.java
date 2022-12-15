package recipes.rest.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import recipes.domain.entity.Recipe;
import recipes.rest.dto.RecipeDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ListRecipeMapper {
    @Autowired
    RecipeMapper recipeMapper;

    public List<RecipeDTO> toRecipeDTOList(final List<Recipe> recipes){
        return recipes.stream()
                .map(recipe -> recipeMapper.toRecipeDTO(recipe))
                .collect(Collectors.toList());
    }
}
