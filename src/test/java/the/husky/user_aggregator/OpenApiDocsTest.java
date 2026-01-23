package the.husky.user_aggregator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class for logging OpenAPI documentation
 */
@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
class OpenApiDocsTest extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void logOpenApiDocs() throws Exception {
        String openApiDocs = mockMvc.perform(get("/v3/api-docs")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        // Convert JSON to YAML
        JsonNode jsonNode = objectMapper.readTree(openApiDocs);
        YAMLMapper yamlMapper = new YAMLMapper();
        String yamlOutput = yamlMapper.writeValueAsString(jsonNode);

        log.info("=== OpenAPI Documentation (YAML) ===");
        log.info("\n{}", yamlOutput);
        log.info("=== End of OpenAPI Documentation ===");
    }
}
