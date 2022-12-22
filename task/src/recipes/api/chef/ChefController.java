package recipes.api.chef;

import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import recipes.api.chef.dto.ChefCreateCommand;
import recipes.api.chef.mappers.ChefMapper;
import recipes.service.ChefDetailsServiceImpl;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@Validated
@RequestMapping("api")
@AllArgsConstructor
public class ChefController {

    private final ChefDetailsServiceImpl chefDetailsService;
    private final ChefMapper chefMapper;

    @PostMapping("/register")
    public void create(@RequestBody @Valid ChefCreateCommand command) {
        Optional.ofNullable(command)
                .map(chefMapper::mapFromCreateCommandToDomain)
                .ifPresent(chefDetailsService::add);
    }

}
