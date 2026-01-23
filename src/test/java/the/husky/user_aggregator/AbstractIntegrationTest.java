package the.husky.user_aggregator;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 * Abstract base class for integration tests with PostgreSQL and MySQL containers
 */
@Testcontainers
@ActiveProfiles("test")
public abstract class AbstractIntegrationTest {

	private static PostgreSQLContainer<?> POSTGRES_CONTAINER = new PostgreSQLContainer<>("postgres:15-alpine")
			.withDatabaseName("postgres")
			.withInitScript("db-init/postgres-init.sql");

	private static MySQLContainer<?> MYSQL_CONTAINER = new MySQLContainer<>("mysql:8.0")
			.withDatabaseName("mysql_db")
			.withInitScript("db-init/mysql-init.sql");

	static {
		POSTGRES_CONTAINER.start();
		MYSQL_CONTAINER.start();
	}

	@DynamicPropertySource
	static void configureProperties(DynamicPropertyRegistry registry) {
		registry.add("test.postgres.url", POSTGRES_CONTAINER::getJdbcUrl);
		registry.add("test.postgres.user", POSTGRES_CONTAINER::getUsername);
		registry.add("test.postgres.password", POSTGRES_CONTAINER::getPassword);
		
		registry.add("test.mysql.url", () -> MYSQL_CONTAINER.getJdbcUrl() + "?useSSL=false&allowPublicKeyRetrieval=true");
		registry.add("test.mysql.user", MYSQL_CONTAINER::getUsername);
		registry.add("test.mysql.password", MYSQL_CONTAINER::getPassword);
	}

}
