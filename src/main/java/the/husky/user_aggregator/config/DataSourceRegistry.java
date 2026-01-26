package the.husky.user_aggregator.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class DataSourceRegistry {
    private final Map<String, JdbcTemplate> jdbcTemplates = new HashMap<>();

    public void registerDataSource(DataSourceProperties props) {
        log.info("Registering data source: {}", props.getDbName());
        
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(props.getUrl());
        if (props.getUser() != null && !props.getUser().isEmpty()) {
            config.setUsername(props.getUser());
        }
        if (props.getPassword() != null && !props.getPassword().isEmpty()) {
            config.setPassword(props.getPassword());
        }
        config.setDriverClassName(getDriverClassName(props.getStrategy()));
        config.setPoolName(props.getDbName() + "-pool");
        config.setMaximumPoolSize(5);
        config.setMinimumIdle(1);
        config.setConnectionTimeout(30000);
        config.setIdleTimeout(600000);
        config.setMaxLifetime(1800000);

        DataSource dataSource = new HikariDataSource(config);
        jdbcTemplates.put(props.getDbName(), new JdbcTemplate(dataSource));
        
        log.info("Successfully registered data source: {}", props.getDbName());
    }

    public JdbcTemplate getJdbcTemplate(String name) {
        JdbcTemplate template = jdbcTemplates.get(name);
        if (template == null) {
            throw new IllegalArgumentException("Data source not found: " + name);
        }
        return template;
    }

    private String getDriverClassName(String strategy) {
        return switch (strategy.toLowerCase()) {
            case "postgres" -> "org.postgresql.Driver";
            case "mysql" -> "com.mysql.cj.jdbc.Driver";
            default -> throw new IllegalArgumentException("Unknown database strategy: " + strategy);
        };
    }
}
