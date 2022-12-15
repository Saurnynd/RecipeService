package recipes.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import recipes.domain.entity.Chef;
import recipes.service.ChefDetailsServiceImpl;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping("api")
public class RegisterController {

    private final ChefDetailsServiceImpl chefDetailsService;

    @Autowired
    public RegisterController(final ChefDetailsServiceImpl chefDetailsService) {
        this.chefDetailsService = chefDetailsService;
    }

    @PostMapping("/register")
    public void testAuth(@RequestBody @Valid Chef chef) {
        chefDetailsService.addChef(chef);
    }

}
