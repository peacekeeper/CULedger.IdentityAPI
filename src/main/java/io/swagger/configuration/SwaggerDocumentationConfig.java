package io.swagger.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-03-02T19:50:39.116Z[GMT]")
@Configuration
public class SwaggerDocumentationConfig {

    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("CULedger.Identity API")
            .description("This is the base Identity API for CULedger's MyCUID platform.  It is focused on the Identity portions of CULedger, which will be used by multiple stakeholders in the CULedger ecosystem. It maps Members (by memberId) to the private DID Pairs that establish a privacy-respecting, secure connection between a Member and a financial institution. The machinations of Sovrin communications and credential assembly and proofing are handled internally to the CULedger Identity API. During the Onboard() process the following happens * A secure Connection is established with the Member - and each end of the connection has a unique decentralized identifier (DID), which include a public and private keypair. This private \"pairwise\" connection is unique at both ends of the connection - at the FI and at the User. All communications travel across this authenticated connection once it has been established. * Once the connection is made, a Credential is Issued and sent to the Member. The Member's digital wallet (e.g. Connect.Me) has the ability to digital prove that they control the device later as their DID is tied to a link secret that is stored in the secure enclave/trusted execution environment of their smartphone.   v0.2.0 adds polling in for support of the long-running commands - both .Onboard() and .Authenticate() tend to take seconds not sub-second. Both depend on user actions at the remote end so can range heavily. Overall, the CULedger.Identity API is intended to do only 2 things outside of configuration and monitoring.  1. Allow a single function call to Onboard a Member - which contacts the member (via SMS or email), gets Connect.Me installed, and sends down a credential from the CU. 2. Allow a single function call to later Authenticate a Member that holds the credential that was issued to them.  The API is Member-centric - it is all about linking a CU Member to a credential.  NOTE the API will evolve to add more capability. This initial, nearly naive, simple API is intentionally limited. The team will make an effort to avoide adding complexity unless it is fully warranted. ")
            .license("")
            .licenseUrl("http://unlicense.org")
            .termsOfServiceUrl("")
            .version("0.2.0")
            .contact(new Contact("","", "darrell.odonnell@culedger.com"))
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
