package com.example.OpenApi.controller;

import com.example.OpenApi.dto.GPTRequest;
import com.example.OpenApi.dto.GPTResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/gpt")
public class GptController {

    @Value("${openai.api.model}")
    private String model;

    @Value("${openai.api.url}")
    private String url;

    @Autowired
    private RestTemplate template;

    @GetMapping("/chat")
    public String chat(@RequestParam("prompt") String prompt) {
        GPTRequest gptRequest = new GPTRequest(model, prompt);
        GPTResponse gptResponse = template.postForObject(url, gptRequest, GPTResponse.class);
        return gptResponse.getChoices().get(0).getMessage().getContent();
    }
}
