package recipes.service;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import recipes.domain.entity.Recipe;
import recipes.domain.repository.RecipeRepository;
import recipes.exceptions.RecipeNotFoundException;
import recipes.security.CurrentChef;

import java.util.*;

@Service
@AllArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;

    private final CurrentChef currentChef;

    public Recipe create(final Recipe recipe) {
        recipe.setChef(currentChef.getCurrentUser().getChef());
        return recipeRepository.save(recipe);
    }

    @Transactional
    public Recipe update(final Recipe recipe, final long id) {
        var toUpdate = findById(id).orElseThrow(() -> new RecipeNotFoundException(""));
        toUpdate.setName(recipe.getName());
        toUpdate.setDescription(recipe.getDescription());
        toUpdate.setCategory(recipe.getCategory());
        toUpdate.setIngredients(recipe.getIngredients());
        toUpdate.setDirections(recipe.getDirections());
        return toUpdate;
    }

    public Optional<Recipe> findById(final long id) {
        return recipeRepository.findById(id);
    }

    public void deleteById(final long id) {
        recipeRepository.deleteById(id);
    }

    public List<Recipe> getAllRecipesByCategory(final String category) {
        return recipeRepository.findByCategoryIgnoreCaseOrderByDateDesc(category);
    }

    public List<Recipe> getAllRecipesByName(final String name) {
        return recipeRepository.findByNameIgnoreCaseContainsOrderByDateDesc(name);
    }

    public List<Recipe> getAllRecipesByNameOrCategory(final Optional<String> name, final Optional<String> category) {
        if (name.isPresent())
            return getAllRecipesByName(name.get());
        if (category.isPresent())
            return getAllRecipesByCategory(category.get());
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
}
