package com.st.authlight;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import static org.springframework.boot.SpringApplication.run;

@Slf4j
@SpringBootApplication
@ConfigurationPropertiesScan("com.st.authlight")
public class AuthLightTraiderApplication {

    public static void main(String[] args) {
        var env = run(AuthLightTraiderApplication.class, args).getEnvironment();

        log.info("""
                        ----------------------------------------------------------
                        \tApplication '{}' is running!\s
                        \tAccess URL: http://localhost:{}
                        \tSwagger UI: http://localhost:{}/swagger-ui.html
                        \tProfile(s): {}
                        ----------------------------------------------------------""",
                env.getProperty("spring.application.name"),
                env.getProperty("server.port"),
                env.getProperty("server.port"),
                env.getActiveProfiles());
    }

}
