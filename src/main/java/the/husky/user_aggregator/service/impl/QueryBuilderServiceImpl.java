package the.husky.user_aggregator.service.impl;

import org.springframework.stereotype.Service;
import the.husky.user_aggregator.config.DataSourceProperties;
import the.husky.user_aggregator.service.QueryBuilderService;

@Service
public class QueryBuilderServiceImpl implements QueryBuilderService {

    @Override
    public String buildSelectQuery(DataSourceProperties dataSourceProperties) {
        try {
            DataSourceProperties.ColumnMapping mapping = dataSourceProperties.getColumnMapping();
            String table = dataSourceProperties.getTable();

            if (isInvalidDataSourceConfiguration(mapping, table)) {
                throw new IllegalArgumentException(
                        "Data source configuration is incomplete for data source: %s"
                                .formatted(dataSourceProperties.getDbName()));
            }

            return String.format(
                    "SELECT %s AS id, %s AS username, %s AS name, %s AS surname FROM %s",
                    mapping.getId(),
                    mapping.getUsername(),
                    mapping.getName(),
                    mapping.getSurname(),
                    table
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to build query for data source: %s"
                    .formatted(dataSourceProperties.getDbName()), e);
        }
    }

    private boolean isInvalidDataSourceConfiguration(DataSourceProperties.ColumnMapping mapping, String table) {
        return mapping == null || mapping.getId() == null || mapping.getUsername() == null
                || mapping.getName() == null || mapping.getSurname() == null
                || table == null || table.isEmpty();
    }
}
