package the.husky.user_aggregator.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import the.husky.user_aggregator.config.DataSourceConfigProperties;
import the.husky.user_aggregator.config.DataSourceProperties;
import the.husky.user_aggregator.config.DataSourceRegistry;
import the.husky.user_aggregator.dto.UserDto;
import the.husky.user_aggregator.service.UserReaderService;
import the.husky.user_aggregator.service.UsersAggregationService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsersAggregationServiceImpl implements UsersAggregationService {
    
    private final DataSourceConfigProperties configProperties;
    private final DataSourceRegistry dataSourceRegistry;
    private final UserReaderService userReaderService;
    
    @Override
    public List<UserDto> aggregateUsers() {
        List<UserDto> allUsers = new ArrayList<>();
        
        // Iterate through data sources in configured order
        for (DataSourceProperties props : configProperties.getSources()) {
            log.debug("Reading users from data source: {}", props.getName());
            
            try {
                JdbcTemplate jdbcTemplate = dataSourceRegistry.getJdbcTemplate(props.getName());
                List<UserDto> users = userReaderService.readUsersFromSource(jdbcTemplate, props);
                allUsers.addAll(users);
                log.info("Read {} users from data source: {}", users.size(), props.getName());
            } catch (Exception e) {
                log.error("Error reading users from data source: {}", props.getName(), e);
                throw new RuntimeException("Failed to read users from data source: " + props.getName(), e);
            }
        }
        
        log.info("Total users aggregated: {}", allUsers.size());
        return allUsers;
    }
}
