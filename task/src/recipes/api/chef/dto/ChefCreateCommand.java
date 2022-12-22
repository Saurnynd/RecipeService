package recipes.api.chef.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Builder
@Getter
public class ChefCreateCommand {
    private static final String REGEX_PATTERN = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";

    @Pattern(regexp = REGEX_PATTERN)
    private String email;

    @Size(min = 8, max = 255)
    @NotBlank
    private String password;

}
