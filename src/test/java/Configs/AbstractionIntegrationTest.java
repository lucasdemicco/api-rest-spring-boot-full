package Configs;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.lifecycle.Startables;

import java.util.Map;
import java.util.stream.Stream;

@ContextConfiguration(initializers = AbstractionIntegrationTest.Initializer.class)
public class AbstractionIntegrationTest {
    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:9.6.12");

        private static void startContainer(){
            Startables.deepStart(Stream.of(postgres)).join();
        }

        private static Map<String, Object> createConnectionConfiguration() {
            return Map.of(
                    "spring.datasource.url", postgres.getJdbcUrl(),
                    "spring.datasource.username", postgres.getUsername(),
                    "spring.datasource.password", postgres.getPassword()
            );
        }

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            startContainer();
            ConfigurableEnvironment environment = applicationContext.getEnvironment();
            MapPropertySource testContainers = new MapPropertySource(
                    "testContainers", createConnectionConfiguration());

            environment.getPropertySources().addFirst(testContainers);
        }
    }
}
