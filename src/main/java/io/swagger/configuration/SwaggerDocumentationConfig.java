package io.swagger.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2018-11-14T21:44:13.544Z[GMT]")

@Configuration
public class SwaggerDocumentationConfig {

    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("CULedger Identity API")
            .description("This is a simple Identity API mockup for CULedger. It is focused on only the Identity portions of CULedger, which will be used by multiple stakeholders in the CULedger ecosystem. It maps Members (by memberId) to the private DID Pairs that establish a privacy-respecting, secure connection between a Member and a financial institution. The machinations of Sovrin communications and credential assembly and proofing are handled internally to the CULedger Identity API.  Overall, the CULedger.Identity API is intended to do only 2 things outside of configuration and monitoring.  1. Allow a single function call to Onboard a Member - which contacts the member (via SMS or email), gets Connect.Me installed, and sends down a credential from the CU. 2. Allow a single function call to later Authenticate a Member that holds the credential that was issued to them.  The API is Member-centric - it is all about  NOTE the API will evolve to add more capability. This initial, nearly naive, simple API is intentionally limited. The team will make an effort to avoide adding complexity unless it is fully warranted. ")
            .license("")
            .licenseUrl("http://unlicense.org")
            .termsOfServiceUrl("")
            .version("0.1.0")
            .contact(new Contact("","", "darrell.odonnell@continuumloop.com"))
            .build();
    }

    @Bean
    public Docket customImplementation(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                    .apis(RequestHandlerSelectors.basePackage("io.swagger.api"))
                    .build()
                .directModelSubstitute(org.threeten.bp.LocalDate.class, java.sql.Date.class)
                .directModelSubstitute(org.threeten.bp.OffsetDateTime.class, java.util.Date.class)
                .apiInfo(apiInfo());
    }

}
