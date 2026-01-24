package the.husky.user_aggregator.service;

import the.husky.user_aggregator.dto.UserDto;

import java.util.List;

/**
 * Service interface for aggregating users from multiple data sources
 */
public interface UsersAggregationService {
    
    /**
     * Aggregates users from all configured data sources sequentially
     * Reads (DB1, then DB2, then DB3, then DB4) to avoid collisions
     * 
     * @return List of users from all databases (approximately 100 users: 25*4)
     * @throws RuntimeException if aggregation fails
     */
    List<UserDto> aggregateUsers();
}
