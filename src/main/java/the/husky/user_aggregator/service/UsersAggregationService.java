package the.husky.user_aggregator.service;

import the.husky.user_aggregator.dto.UserDto;

import java.util.List;


public interface UsersAggregationService {

    List<UserDto> aggregateUsers();
}
