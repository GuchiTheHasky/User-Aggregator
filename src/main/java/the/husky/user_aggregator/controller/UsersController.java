package the.husky.user_aggregator.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import the.husky.user_aggregator.dto.UserDto;
import the.husky.user_aggregator.service.UsersAggregationService;

import java.util.List;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "Users", description = "Users aggregation API")
public class UsersController {
    
    private final UsersAggregationService usersAggregationService;
    
    @GetMapping
    @Operation(
            summary = "Get all users",
            description = "Aggregates users from all configured databases sequentially and returns them as a JSON array",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved users"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    public ResponseEntity<List<UserDto>> getUsers() {
        List<UserDto> users = usersAggregationService.aggregateUsers();
        return ResponseEntity.ok(users);
    }
}
