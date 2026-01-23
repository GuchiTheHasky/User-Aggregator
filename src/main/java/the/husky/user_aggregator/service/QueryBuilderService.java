package the.husky.user_aggregator.service;

import the.husky.user_aggregator.config.DataSourceProperties;

/**
 * Service for building SQL queries based on data source configuration
 */
public interface QueryBuilderService {
    
    /**
     * Builds a SELECT query with column aliases based on the mapping configuration
     * 
     * @param props Data source properties with table and column mapping
     * @return SQL query string
     * @throws IllegalArgumentException if configuration is invalid
     */
    String buildSelectQuery(DataSourceProperties props);
    
    /**
     * Escapes SQL identifier (column or table name) based on database strategy
     * 
     * @param identifier Identifier name to escape (column or table)
     * @param strategy Database strategy (postgres, mysql)
     * @return Escaped identifier
     * @throws IllegalArgumentException if identifier is null or empty
     */
    String escapeIdentifier(String identifier, String strategy);
}
