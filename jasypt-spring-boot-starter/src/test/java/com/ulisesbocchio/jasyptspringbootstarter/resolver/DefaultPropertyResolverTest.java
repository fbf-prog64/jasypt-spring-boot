package com.ulisesbocchio.jasyptspringbootstarter.resolver;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootApplication
class TestApp {
    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(TestApp.class)
                .run(args);
    }
}
@SpringBootTest(
        properties = {
                "spring.config.import=optional:classpath:bootstrap.properties",
                "server.port=9625"
        },
        classes = TestApp.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
        )
class DefaultPropertyResolverTest {

    @Value("${spring.cloud.config.server.svn.password}")
    public String secret;

    public int port = 9625;

    @Autowired
    private Environment env;

    @Test
    void encryptedPropertyIsDecryptedInEnvironment() {
        assertThat(env.getProperty("spring.cloud.config.server.svn.password")).isEqualTo("mypassword");
        assertThat(secret).isEqualTo("mypassword");
    }

    @Test
    void propertiesAreAccessibleFromEnvActuator() {
        ResponseEntity<String> response = RestClient.create()
                .get()
                .uri("http://localhost:" + port + "/actuator/env")
                .retrieve()
                .toEntity(String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
