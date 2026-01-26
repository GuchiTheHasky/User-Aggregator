package the.husky.user_aggregator.service;

import org.springframework.jdbc.core.JdbcTemplate;
import the.husky.user_aggregator.config.DataSourceProperties;
import the.husky.user_aggregator.dto.UserDto;

import java.util.List;


public interface UserReaderService {

    List<UserDto> readUsersFromSource(JdbcTemplate jdbcTemplate, DataSourceProperties props);
}
