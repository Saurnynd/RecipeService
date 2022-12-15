package recipes.rest.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import recipes.domain.entity.Recipe;
import recipes.rest.dto.NewRecipeDTO;
import recipes.rest.dto.RecipeDTO;
import recipes.rest.mappers.ListRecipeMapper;
import recipes.rest.mappers.NewRecipeMapper;
import recipes.rest.mappers.RecipeMapper;
import recipes.security.ChefDetailsImpl;
import recipes.service.RecipeService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Optional;


@RestController
@Validated
@RequestMapping("api/recipe")
public class RecipeController {

    private final RecipeService recipeService;

    @Autowired
    private NewRecipeMapper newRecipeMapper;

    @Autowired
    private RecipeMapper recipeMapper;

    @Autowired
    private ListRecipeMapper listRecipeMapper;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping("/new")
    public @ResponseBody
    NewRecipeDTO addRecipe(@AuthenticationPrincipal ChefDetailsImpl details, @RequestBody @Valid final Recipe recipe) {
        recipe.setChef(details.getChef());
        return newRecipeMapper.toNewRecipeDTO(recipeService.addRecipe(recipe));
    }

    @GetMapping("/{id}")
    public @ResponseBody RecipeDTO responseRecipe(@PathVariable @Min(1) final long id) {
        return recipeMapper.toRecipeDTO(recipeService.findById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<?> responseRecipe(@RequestParam(required = false) final Optional<String> name,
                                            @RequestParam(required = false) final Optional<String> category) {

        if (name.isPresent())
            return ResponseEntity.status(HttpStatus.OK)
                    .body(listRecipeMapper
                            .toRecipeDTOList(recipeService
                                    .getAllRecipesByName(name.get())));

        if (category.isPresent())
            return ResponseEntity.status(HttpStatus.OK)
                    .body(listRecipeMapper
                            .toRecipeDTOList(recipeService
                                    .getAllRecipesByCategory(category.get())));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");

    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PreAuthorize("@recipeOwnershipEvaluator.isOwner(#id, principal)")
    public @ResponseBody
    RecipeDTO putRecipe(@RequestBody @Valid final Recipe recipe, @PathVariable @Min(1) final long id) {

        return recipeMapper.toRecipeDTO(recipeService.putRecipe(recipe, id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PreAuthorize("@recipeOwnershipEvaluator.isOwner(#id, principal)")
    public @ResponseBody void deleteRecipe(@PathVariable @Min(1) final long id) {
        recipeService.deleteById(id);
    }
}
