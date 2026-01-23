package the.husky.user_aggregator.service;

import org.springframework.jdbc.core.JdbcTemplate;
import the.husky.user_aggregator.config.DataSourceProperties;
import the.husky.user_aggregator.dto.UserDto;

import java.util.List;

/**
 * Service for reading users from a single data source
 */
public interface UserReaderService {
    
    /**
     * Reads users from a single data source using dynamic SQL based on column mapping
     * 
     * @param jdbcTemplate JdbcTemplate for the data source
     * @param props Data source properties with configuration
     * @return List of users from the data source
     * @throws RuntimeException if reading fails
     */
    List<UserDto> readUsersFromSource(JdbcTemplate jdbcTemplate, DataSourceProperties props);
}
