package com.group40.hjemmesalgrestws;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
//http://localhost:8080/Homely-ws/v2/api-docs ->json format
//http://localhost:8080/Homely-ws/swagger-ui.html#/ ->hjemmeside
public class SwaggerConfiguration {

    Contact contact = new Contact("Janus & Company",
                                        "Vi har ikke en url",
                                        "s175129@student.dk");
    List<VendorExtension> vendorExtensions = new ArrayList<>(); //Anvend hvis der er specifikationer, som ikke dækkes af standard dokumentationen for SpringFox
    ApiInfo apiInfo = new ApiInfo(
            "Homely REST Api dokumentation",
            "Dokumentation af endpoints udviklere kan tilkoble sig",
            "1.0",
            "DENNE UDVIKLER URL ER IKKE AKTIV DA DEN IKKE ER UDARBEJDET",
            contact,
            "Apache 2.0",
            "http://www.apache.org/licenses/LICENSE-2.0",
            vendorExtensions);

    @Bean
    public Docket apiDocket(){

        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.group40.hjemmesalgrestws")) //fortæller swagger hvilke klasser der skal inkluderes i dokumentationen
                .paths(PathSelectors.any()) //Alle endpoints der annoteres med @path vælges
                .build();

        return docket;
    }
}
