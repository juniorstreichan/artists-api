package dev.juniorstreichan.artists.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket apiDetails() {

        var docket = new Docket(DocumentationType.SWAGGER_2);

        docket
            .select()
            .apis(RequestHandlerSelectors.basePackage("dev.juniorstreichan.artists"))
            .paths(PathSelectors.any())
            .build()
            .apiInfo(this.apiInfo().build());

        return docket;
    }

    private ApiInfoBuilder apiInfo() {

        var apiInfoBuilder = new ApiInfoBuilder();

        apiInfoBuilder.title("Api-Artistas");
        apiInfoBuilder.description("Api para realização de CRUD de Artistas.");
        apiInfoBuilder.version("1.0");
        apiInfoBuilder.termsOfServiceUrl("https://opensource.org/licenses/MIT");
        apiInfoBuilder.license("Licença MIT - Open Source");
        apiInfoBuilder.licenseUrl("https://opensource.org/licenses/MIT");
        apiInfoBuilder.contact(this.contact());

        return apiInfoBuilder;

    }

    private Contact contact() {

        return new Contact(
            "Junior Erdmann Streichan",
            "https://github.com/juniorstreichan",
            "juniorstreichan@gmail.com");
    }
}
