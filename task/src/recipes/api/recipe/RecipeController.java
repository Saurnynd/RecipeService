package recipes.api.recipe;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import recipes.api.recipe.dto.RecipeCreateCommand;
import recipes.api.recipe.dto.RecipeDTO;
import recipes.api.recipe.dto.RecipeFilter;
import recipes.api.recipe.dto.RecipeUpdateCommand;
import recipes.api.recipe.mappers.RecipeMapper;
import recipes.exceptions.RecipeNotFoundException;
import recipes.service.RecipeService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;


@RestController
@Validated
@RequestMapping("api/recipe")
@AllArgsConstructor
public class RecipeController {
    private final RecipeService recipeService;
    private final RecipeMapper recipeMapper;

    @PostMapping("/new")
    public @ResponseBody
    RecipeFilter create(@RequestBody @Valid final RecipeCreateCommand command) {
        return Optional.ofNullable(command)
                .map(recipeMapper::mapFromCreateCommandToDomain)
                .map(recipeService::create)
                .map(recipeMapper::mapFromDomainToFilter).get();
    }

    @GetMapping("/{id}")
    public @ResponseBody RecipeDTO get(@PathVariable @Min(1) final long id) {
        return recipeMapper.mapFromDomainToDto(recipeService.findById(id)
                .orElseThrow(()->new RecipeNotFoundException("")));
    }

    @GetMapping("/search")
    public @ResponseBody List<RecipeDTO> getByNameByCategory(@RequestParam(required = false) final Optional<String> name,
                                                             @RequestParam(required = false) final Optional<String> category) {
        return recipeService.getAllRecipesByNameOrCategory(name, category).stream()
                .map(recipeMapper::mapFromDomainToDto).toList();
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PreAuthorize("@recipeOwnershipEvaluator.isOwner(#id, principal)")
    public @ResponseBody
    RecipeDTO update(@RequestBody @Valid final RecipeUpdateCommand command, @PathVariable @Min(1) final long id) {
        return Optional.ofNullable(command)
                .map(recipeMapper::mapFromUpdateCommandToDomain)
                .map(recipe -> recipeService.update(recipe, id))
                .map(recipeMapper::mapFromDomainToDto).get();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PreAuthorize("@recipeOwnershipEvaluator.isOwner(#id, principal)")
    public @ResponseBody void delete(@PathVariable @Min(1) final long id) {
        recipeService.deleteById(id);
    }
}
