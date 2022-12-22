package recipes.api.recipe.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Builder
@Getter
public class RecipeCreateCommand {

    @NotBlank
    private String name;

    @NotBlank
    private String category;

    @NotBlank
    private String description;

    @NotEmpty
    private String[] ingredients;

    @NotEmpty
    private String[] directions;
}
