package phi.sev.catsbackend.adapters.configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.text.SimpleDateFormat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import phi.sev.catsbackend.adapters.persistence.CatDBAdapter;
import phi.sev.catsbackend.application.services.CatService;

@Configuration
public class BeanConfiguration {

    @Bean
    CatService catService(CatDBAdapter repository) {
        return new CatService(repository, repository);
    }

    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setDateFormat(new SimpleDateFormat(DATE_TIME_FORMAT));
        objectMapper.registerModule(new JavaTimeModule());

        return objectMapper;
    }
}
