package the.husky.user_aggregator.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ConfigurationProperties(prefix = "data-sources")
public class DataSourceConfigProperties {
    
    private List<DataSourceProperties> databases = new ArrayList<>();
}
