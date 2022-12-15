package recipes.rest.controller.tools;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import recipes.domain.entity.Recipe;
import recipes.service.RecipeService;


@Component(value = "recipeOwnershipEvaluator")
public class RecipeOwnershipEvaluator {

    private final RecipeService recipeService;

    @Autowired
    public RecipeOwnershipEvaluator(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    public boolean isOwner(long id, UserDetails userDetails) {
        Recipe toModify = recipeService.findById(id);
        return toModify.getChef().getEmail().equals(userDetails.getUsername());
    }
}
