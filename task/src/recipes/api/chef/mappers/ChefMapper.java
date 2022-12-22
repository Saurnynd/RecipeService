package recipes.api.chef.mappers;

import org.springframework.stereotype.Component;
import recipes.api.chef.dto.ChefCreateCommand;
import recipes.domain.entity.Chef;

@Component
public class ChefMapper {
    public Chef mapFromCreateCommandToDomain(ChefCreateCommand command) {
        return Chef.builder()
                .email(command.getEmail())
                .password(command.getPassword())
                .build();
    }
}
