package com.api.insurances.backend.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import org.junit.jupiter.api.Test;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class SwaggerConfigTest {

    @Test
    public void testCustomOpenAPI() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SwaggerConfig.class);
        OpenAPI openAPI = context.getBean(OpenAPI.class);

        assertThat(openAPI).isNotNull();
        assertThat(openAPI.getInfo()).isNotNull();
        assertThat(openAPI.getInfo().getTitle()).isEqualTo("Insurance API");
        assertThat(openAPI.getInfo().getVersion()).isEqualTo("1.0");
        assertThat(openAPI.getInfo().getDescription()).isEqualTo("API to manage insurance registration");
    }

    @Test
    public void testPublicApi() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SwaggerConfig.class);
        GroupedOpenApi groupedOpenApi = context.getBean(GroupedOpenApi.class);

        assertThat(groupedOpenApi).isNotNull();
        assertThat(groupedOpenApi.getGroup()).isEqualTo("public");
        assertThat(groupedOpenApi.getPathsToMatch()).containsExactly("/api/**");
    }
}