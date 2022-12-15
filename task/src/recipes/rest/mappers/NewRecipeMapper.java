package recipes.rest.mappers;

import org.springframework.stereotype.Component;
import recipes.rest.dto.NewRecipeDTO;

@Component
public class NewRecipeMapper {

    public NewRecipeDTO toNewRecipeDTO(final long id){
        return NewRecipeDTO.builder().id(id).build();
    }
}
