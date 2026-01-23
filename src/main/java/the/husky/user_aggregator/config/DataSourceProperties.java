package the.husky.user_aggregator.config;

import lombok.Getter;
import lombok.Setter;

/**
 * Configuration for a single data source with column mapping
 */
@Getter
@Setter
public class DataSourceProperties {
    private String name;
    private String strategy; // postgres, mysql
    private String url;
    private String user;
    private String password;
    private String table;
    private ColumnMapping mapping;
    
    @Getter
    @Setter
    public static class ColumnMapping {
        private String id;
        private String username;
        private String name;
        private String surname;
    }
}
