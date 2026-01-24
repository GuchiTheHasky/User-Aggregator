package the.husky.user_aggregator.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Schema(description = "User information")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    
    @Schema(description = "User ID", example = "1")
    private String id;
    
    @Schema(description = "Username", example = "jdoe")
    private String username;
    
    @Schema(description = "First name", example = "John")
    private String name;
    
    @Schema(description = "Last name", example = "Doe")
    private String surname;
}
