package recipes.api.recipe.mappers;

import org.springframework.stereotype.Component;
import recipes.api.recipe.dto.RecipeFilter;
import recipes.api.recipe.dto.RecipeUpdateCommand;
import recipes.domain.entity.Recipe;
import recipes.api.recipe.dto.RecipeCreateCommand;
import recipes.api.recipe.dto.RecipeDTO;

import java.util.Arrays;

@Component
public class RecipeMapper {

    public RecipeDTO mapFromDomainToDto(final Recipe recipe) {
        return RecipeDTO.builder()
                .name(recipe.getName())
                .description(recipe.getDescription())
                .category(recipe.getCategory())
                .date(recipe.getDate())
                .directions(Arrays.stream(recipe.getDirections()).toArray(String[]::new))
                .ingredients(Arrays.stream(recipe.getIngredients()).toArray(String[]::new))
                .build();
    }

    public Recipe mapFromCreateCommandToDomain(final RecipeCreateCommand command) {
        return Recipe.builder()
                .description(command.getDescription())
                .directions(command.getDirections())
                .ingredients(command.getIngredients())
                .name(command.getName())
                .category(command.getCategory())
                .build();
    }

    public Recipe mapFromUpdateCommandToDomain(final RecipeUpdateCommand command) {
        return Recipe.builder()
                .description(command.getDescription())
                .directions(command.getDirections())
                .ingredients(command.getIngredients())
                .name(command.getName())
                .category(command.getCategory())
                .build();
    }

    public RecipeFilter mapFromDomainToFilter(Recipe recipe) {
        return RecipeFilter.builder().id(recipe.getRecipeId()).build();
    }
}
