package recipes.api.recipe.validators;


import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import recipes.domain.entity.Recipe;
import recipes.exceptions.RecipeNotFoundException;
import recipes.service.RecipeService;


@Component(value = "recipeOwnershipEvaluator")
@AllArgsConstructor
public class RecipeOwnershipEvaluator {

    private final RecipeService recipeService;

    public boolean isOwner(long id, UserDetails userDetails) {
        Recipe toModify = recipeService.findById(id).orElseThrow(() -> new RecipeNotFoundException(""));
        return toModify.getChef().getEmail().equals(userDetails.getUsername());
    }
}
