package the.husky.user_aggregator.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import the.husky.user_aggregator.config.DataSourceProperties;
import the.husky.user_aggregator.service.QueryBuilderService;

@Slf4j
@Service
public class QueryBuilderServiceImpl implements QueryBuilderService {
    
    @Override
    public String buildSelectQuery(DataSourceProperties props) {
        try {
            DataSourceProperties.ColumnMapping mapping = props.getMapping();
            String table = props.getTable();
            String strategy = props.getStrategy();
            
            if (mapping == null || mapping.getId() == null || mapping.getUsername() == null 
                    || mapping.getName() == null || mapping.getSurname() == null) {
                throw new IllegalArgumentException("Column mapping is incomplete for data source: " + props.getName());
            }
            
            if (table == null || table.isEmpty()) {
                throw new IllegalArgumentException("Table name is not specified for data source: " + props.getName());
            }
            
            // Build query with column aliases
            return String.format(
                    "SELECT %s AS id, %s AS username, %s AS name, %s AS surname FROM %s",
                    escapeIdentifier(mapping.getId(), strategy),
                    escapeIdentifier(mapping.getUsername(), strategy),
                    escapeIdentifier(mapping.getName(), strategy),
                    escapeIdentifier(mapping.getSurname(), strategy),
                    escapeIdentifier(table, strategy)
            );
        } catch (Exception e) {
            log.error("Error building query for data source: {}", props.getName(), e);
            throw new RuntimeException("Failed to build query for data source: " + props.getName(), e);
        }
    }
    
    @Override
    public String escapeIdentifier(String identifier, String strategy) {
        if (identifier == null || identifier.isEmpty()) {
            throw new IllegalArgumentException("Identifier cannot be null or empty");
        }
        
        return "mysql".equalsIgnoreCase(strategy)
                ? "`" + identifier + "`"
                : "\"" + identifier + "\"";
    }
}
