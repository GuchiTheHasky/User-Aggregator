package the.husky.user_aggregator.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class DataSourceConfiguration {
    
    private final DataSourceConfigProperties configProperties;
    private final DataSourceRegistry dataSourceRegistry;
    
    @PostConstruct
    public void initializeDataSources() {
        log.info("Initializing {} data sources", configProperties.getSources().size());
        for (DataSourceProperties props : configProperties.getSources()) {
            try {
                dataSourceRegistry.registerDataSource(props);
            } catch (Exception e) {
                log.error("Failed to initialize data source: {}", props.getName(), e);
                throw new RuntimeException("Failed to initialize data source: " + props.getName(), e);
            }
        }
        log.info("All data sources initialized successfully");
    }
}
