package com.odd.common.util;

import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


/**
 * Author: cao_yang
 * Date: 2024/3/7
 */

public class RestClient {
    private final RestTemplate restTemplate;
    private final HttpHeaders headers;
    private final MultiValueMap<String, Object> params;
    private String bodyJson;

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    public RestClient(){
        this.restTemplate = new RestTemplate();
        this.headers = new HttpHeaders();
        this.params = new LinkedMultiValueMap<>();
    }

    public static class RestClientBuilder {
        private final RestClient client = new RestClient();

        public RestClientBuilder setContentType(MediaType mediaType){
            this.client.headers.setContentType(mediaType);
            return this;
        }

        public RestClientBuilder addHeader(String key, String value) {
            this.client.headers.add(key, value);
            return this;
        }

        public RestClientBuilder addParam(String key, Object value) {
            this.client.params.add(key, value);
            return this;
        }

        public RestClientBuilder setBody(String bodyJson){
            this.client.bodyJson = bodyJson;
            return this;
        }

        public String get(String url) {
            HttpEntity<String> requestEntity = new HttpEntity<>(client.params.toString(), client.headers);
            ResponseEntity<String> response = client.restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
            return response.getBody();
        }

        public String postForm(String url) {
            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(client.params, client.headers);
            ResponseEntity<String> response = client.restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
            return response.getBody();
        }

        public String postBody(String url){
            HttpEntity<String> requestEntity = new HttpEntity<>(client.bodyJson, client.headers);
            ResponseEntity<String> response = client.restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
            return response.getBody();
        }
    }
}
