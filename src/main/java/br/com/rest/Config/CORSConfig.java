package br.com.rest.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CORSConfig  implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry){
            registry.addMapping("/**")
                    .allowedOrigins("*")
                    .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE")
                    .allowedHeaders("*");
                    //.allowCredentials(true); Quando true, não é permitido allowedOrigins para todos
                    // necessário permissão específica de Origin
    }
}
