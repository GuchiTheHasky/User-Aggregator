package the.husky.user_aggregator.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import the.husky.user_aggregator.config.DataSourceProperties;
import the.husky.user_aggregator.dto.UserDto;
import the.husky.user_aggregator.mapper.UserMapper;
import the.husky.user_aggregator.service.QueryBuilderService;
import the.husky.user_aggregator.service.UserReaderService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserReaderServiceImpl implements UserReaderService {
    
    private final QueryBuilderService queryBuilderService;
    private final UserMapper userMapper;
    
    @Override
    public List<UserDto> readUsersFromSource(JdbcTemplate jdbcTemplate, DataSourceProperties dataSourceProperties) {
        try {
            String selectQuery = queryBuilderService.buildSelectQuery(dataSourceProperties);
            log.debug("Executing query: {}", selectQuery);
            
            return jdbcTemplate.query(selectQuery, (resultSet, rowNumber) -> userMapper.mapFromResultSet(resultSet));
        } catch (Exception e) {
            throw new RuntimeException("Failed to execute query for data source: " + dataSourceProperties.getName(), e);
        }
    }
}
