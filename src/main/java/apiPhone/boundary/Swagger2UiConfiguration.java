/**
 * Contains Swagger UI
 */
package apiPhone.boundary;

import apiPhone.control.CustomerController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger UI setup
 */
@EnableSwagger2
@PropertySource("classpath:swagger.properties")
@ComponentScan(basePackageClasses = CustomerController.class)
@Configuration
public class Swagger2UiConfiguration extends WebMvcConfigurerAdapter {
    private static final String SWAGGER_API_VERSION = "1.1";
    private static final String title = "Telecom API";
    private static final String description = "AND Digital Coding Challenge";

    private ApiInfo apiInformation(){
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .version(SWAGGER_API_VERSION)
                .build();
    }

    /**
     * Building swagger documentation
     * @return
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInformation())
                .select()
                .paths(PathSelectors.regex("/api.*"))
                .build();
    }
}
