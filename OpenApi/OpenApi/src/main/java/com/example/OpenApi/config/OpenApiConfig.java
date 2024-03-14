package com.example.OpenApi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OpenApiConfig {

    @Value("${openai.api.key}")
    private String openaiKey;

    @Bean
    public RestTemplate template() {
        RestTemplate template = new RestTemplate();
        template.getInterceptors().add((request, body, execution) -> {
           request.getHeaders().add("Authorization", "Bearer " + openaiKey);
           return execution.execute(request, body);
        });

        return template;
    }
}
