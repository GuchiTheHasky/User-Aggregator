package the.husky.user_aggregator.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataSourceProperties {
    private String dbName;
    private String strategy;
    private String url;
    private String user;
    private String password;
    private String table;
    private ColumnMapping columnMapping;
    
    @Getter
    @Setter
    public static class ColumnMapping {
        private String id;
        private String username;
        private String name;
        private String surname;
    }
}
