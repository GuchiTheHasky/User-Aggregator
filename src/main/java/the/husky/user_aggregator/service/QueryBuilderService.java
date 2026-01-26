package the.husky.user_aggregator.service;

import the.husky.user_aggregator.config.DataSourceProperties;

public interface QueryBuilderService {

    String buildSelectQuery(DataSourceProperties props);
}
