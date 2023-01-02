package phi.sev.catsbackend.adapters.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.UUID;
import javax.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import phi.sev.catsbackend.adapters.persistence.SpringDataCatRepository;
import phi.sev.catsbackend.application.domain.CatStatus;

@Transactional
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class CatControllerToEndTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    SpringDataCatRepository repository;

    @BeforeEach
    void setup() {
        repository.deleteAll();
    }

    @Test
    void createCatShouldAddCatToDatabaseAndReturnUri() throws Exception {
        // given
        final String givenCatName = "Meoweee";
        final String catRequestContent = mapper.writeValueAsString(new CatCreateRequest(givenCatName));

        // when + then
        final MvcResult mvcResult = mockMvc.perform(post("/cats")
                .content(catRequestContent)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isCreated())
            .andExpect(header().exists(HttpHeaders.LOCATION)).andReturn();

        // and
        final String headerLocationValue = (String) mvcResult.getResponse().getHeaderValue(HttpHeaders.LOCATION);
        final UUID catId = UUID.fromString(StringUtils.substringAfter(headerLocationValue, "/cats/"));

        // and
        mockMvc.perform(MockMvcRequestBuilders.get("/cats/{id}", catId))
            .andExpectAll(
                status().isOk(),
                jsonPath("name").value(givenCatName),
                jsonPath("status").value(CatStatus.HAPPY.name())
            );
    }

    @Test
    void getNonExistingCatShouldReturn404() throws Exception {
        // given
        UUID nonExistingCat = UUID.randomUUID();

        // when + then
        mockMvc.perform(get("/cats/{id}", nonExistingCat))
            .andExpect(status().isNotFound());
    }
}