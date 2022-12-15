package recipes.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import recipes.domain.entity.Recipe;
import recipes.repository.RecipeRepository;
import recipes.rest.exceptions.UserNotFoundException;

import java.util.*;

@Service
public class RecipeService {


    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository repository) {
        this.recipeRepository = repository;
    }

    public List<Recipe> getAllRecipes() {
        List<Recipe> recipeAll = new ArrayList<>();
        recipeRepository.findAll().forEach(recipeAll::add);
        return recipeAll;
    }

    public long addRecipe(final Recipe recipe) {
        return recipeRepository.save(recipe).getRecipeId();
    }

    @Transactional
    public Recipe putRecipe(final Recipe recipe, final long id) {
        var toUpdate = findById(id);
        toUpdate.setName(recipe.getName());
        toUpdate.setDescription(recipe.getDescription());
        toUpdate.setCategory(recipe.getCategory());
        toUpdate.setIngredients(recipe.getIngredients());
        toUpdate.setDirections(recipe.getDirections());
        return toUpdate;
    }

    public Recipe findById(final long id) {
        return recipeRepository.findById(id).orElseThrow(() -> new UserNotFoundException(""));
    }

    public void deleteById(final long id) {
        findById(id);
        recipeRepository.deleteById(id);
    }

    public List<Recipe> getAllRecipesByCategory(final String category) {
        return recipeRepository.findByCategoryIgnoreCaseOrderByDateDesc(category);
    }

    public List<Recipe> getAllRecipesByName(final String name) {
        return recipeRepository.findByNameIgnoreCaseContainsOrderByDateDesc(name);
    }
}
