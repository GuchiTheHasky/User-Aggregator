package the.husky.user_aggregator.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Root configuration properties for all data sources
 */
@ConfigurationProperties(prefix = "data-sources")
@Getter
@Setter
public class DataSourceConfigProperties {
    
    private List<DataSourceProperties> sources = new ArrayList<>();
}
