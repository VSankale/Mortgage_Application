package nl.ing.app.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.jakarta.rs.json.JacksonJsonProvider;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;
import nl.ing.app.App;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;

import java.util.Set;
import java.util.stream.Collectors;

@Configuration
@Slf4j
public class AppConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        objectMapper.disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);

        objectMapper.registerModule(new JavaTimeModule());

        return objectMapper;
    }


    @Bean
    public ResourceConfig customJerseyConfig(ObjectMapper objectMapper) {


        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(
                false);
        scanner.addIncludeFilter(new AnnotationTypeFilter(Provider.class));
        scanner.addIncludeFilter(new AnnotationTypeFilter(Path.class));

        ResourceConfig config = new ResourceConfig();
        Set<Class<?>> jerseyClasses = getJerseyClasses(config, scanner);

        log.debug("Jersey classes: {}", jerseyClasses);
        config.registerClasses(jerseyClasses);

        config.register(JacksonJsonProvider.class);
        config.register(JacksonFeature.class);
        return config;
    }

    private Set<Class<?>> getJerseyClasses(ResourceConfig resourceConfig, ClassPathScanningCandidateComponentProvider scanner) {
        return scanner.findCandidateComponents(App.class.getPackageName()).stream()
                .filter(beanDefinition -> beanDefinition.getBeanClassName() != null)
                .map(beanDefinition -> ClassUtils.resolveClassName(beanDefinition.getBeanClassName()
                        , resourceConfig.getClassLoader())).collect(Collectors.toSet());
    }


}
