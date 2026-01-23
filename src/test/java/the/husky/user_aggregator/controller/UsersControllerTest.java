package the.husky.user_aggregator.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import the.husky.user_aggregator.AbstractIntegrationTest;
import the.husky.user_aggregator.config.DataSourceRegistry;
import the.husky.user_aggregator.dto.UserDto;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UsersControllerTest extends AbstractIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private DataSourceRegistry dataSourceRegistry;

	@Test
	void testGetUsers() throws Exception {
		// Expected: 5 users from each database = 10 users total
		mockMvc.perform(get("/users")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", isA(List.class)))
				.andExpect(jsonPath("$.length()", is(10))) // 5 users * 2 databases
				.andExpect(jsonPath("$[0].id", notNullValue()))
				.andExpect(jsonPath("$[0].username", notNullValue()))
				.andExpect(jsonPath("$[0].name", notNullValue()))
				.andExpect(jsonPath("$[0].surname", notNullValue()))
				.andExpect(jsonPath("$[*].id", everyItem(notNullValue())))
				.andExpect(jsonPath("$[*].username", everyItem(notNullValue())))
				.andExpect(jsonPath("$[*].name", everyItem(notNullValue())))
				.andExpect(jsonPath("$[*].surname", everyItem(notNullValue())));
	}

	@Test
	void testGetUsersContainsExpectedUsers() throws Exception {
		String response = mockMvc.perform(get("/users")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();

		List<UserDto> users = objectMapper.readValue(
				response,
				objectMapper.getTypeFactory().constructCollectionType(List.class, UserDto.class)
		);

		// Verify we have users from all databases
		boolean hasPostgresUser = users.stream()
				.anyMatch(u -> u.getUsername() != null && u.getUsername().startsWith("pg_user"));
		boolean hasMysqlUser = users.stream()
				.anyMatch(u -> u.getUsername() != null && u.getUsername().startsWith("mysql_user"));

		assertTrue(hasPostgresUser, "Should contain PostgreSQL users");
		assertTrue(hasMysqlUser, "Should contain MySQL users");
	}

}
