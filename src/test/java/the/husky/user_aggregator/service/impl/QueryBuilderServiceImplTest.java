package the.husky.user_aggregator.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import the.husky.user_aggregator.config.DataSourceProperties;

import static org.junit.jupiter.api.Assertions.*;

class QueryBuilderServiceImplTest {

    private QueryBuilderServiceImpl queryBuilderService;

    @BeforeEach
    void setUp() {
        queryBuilderService = new QueryBuilderServiceImpl();
    }

    @Test
    void testBuildSelectQueryForPostgreSQL() {
        DataSourceProperties props = createPostgreSQLProperties();
        
        String query = queryBuilderService.buildSelectQuery(props);
        
        assertNotNull(query);
        assertTrue(query.contains("SELECT"));
        assertTrue(query.contains("user_id"));
        assertTrue(query.contains("login"));
        assertTrue(query.contains("users_pg"));
        assertTrue(query.contains("AS id"));
        assertTrue(query.contains("AS username"));
        assertTrue(query.contains("AS name"));
        assertTrue(query.contains("AS surname"));
        assertTrue(query.contains("FROM"));
    }

    @Test
    void testBuildSelectQueryForMySQL() {
        DataSourceProperties props = createMySQLProperties();
        
        String query = queryBuilderService.buildSelectQuery(props);
        
        assertNotNull(query);
        assertTrue(query.contains("SELECT"));
        assertTrue(query.contains("id"));
        assertTrue(query.contains("username"));
        assertTrue(query.contains("users_mysql"));
        assertTrue(query.contains("AS id"));
        assertTrue(query.contains("AS username"));
        assertTrue(query.contains("AS name"));
        assertTrue(query.contains("AS surname"));
        assertTrue(query.contains("FROM"));
    }

    @Test
    void testBuildSelectQueryThrowsExceptionWhenMappingIsNull() {
        DataSourceProperties props = new DataSourceProperties();
        props.setDbName("test-db");
        props.setTable("users");
        props.setStrategy("postgres");
        props.setColumnMapping(null);
        
        assertThrows(RuntimeException.class, () -> queryBuilderService.buildSelectQuery(props));
    }

    @Test
    void testBuildSelectQueryThrowsExceptionWhenTableIsNull() {
        DataSourceProperties props = new DataSourceProperties();
        props.setDbName("test-db");
        props.setTable(null);
        props.setStrategy("postgres");
        props.setColumnMapping(createColumnMapping("id", "username", "name", "surname"));
        
        assertThrows(RuntimeException.class, () -> queryBuilderService.buildSelectQuery(props));
    }

    private DataSourceProperties createPostgreSQLProperties() {
        DataSourceProperties props = new DataSourceProperties();
        props.setDbName("data-base-1");
        props.setStrategy("postgres");
        props.setTable("users_pg");
        props.setColumnMapping(createColumnMapping("user_id", "login", "first_name", "last_name"));
        return props;
    }

    private DataSourceProperties createMySQLProperties() {
        DataSourceProperties props = new DataSourceProperties();
        props.setDbName("data-base-2");
        props.setStrategy("mysql");
        props.setTable("users_mysql");
        props.setColumnMapping(createColumnMapping("id", "username", "name", "surname"));
        return props;
    }

    private DataSourceProperties.ColumnMapping createColumnMapping(String id, String username, String name, String surname) {
        DataSourceProperties.ColumnMapping mapping = new DataSourceProperties.ColumnMapping();
        mapping.setId(id);
        mapping.setUsername(username);
        mapping.setName(name);
        mapping.setSurname(surname);
        return mapping;
    }
}
