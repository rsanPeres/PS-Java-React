package br.com.banco.api.controller.request;

import br.com.banco.api.controller.response.HttpResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

public class HttpRequest<T> {
    private final RestTemplate restTemplate;

    public HttpRequest() {
        this.restTemplate = new RestTemplate();
    }

    public HttpResponse<T> sendRequest(T requestBody, String url, HttpMethod method) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        RequestEntity<T> requestEntity = new RequestEntity<>(requestBody, headers, method, URI.create(url));
        ResponseEntity<T> responseEntity = restTemplate.exchange(
                requestEntity,
                new ParameterizedTypeReference<T>() {}
        );

        int statusCodeValue = responseEntity.getStatusCodeValue();
        String responseMessage = HttpStatus.valueOf(statusCodeValue).getReasonPhrase();
        T responseBody = responseEntity.getBody();

        return new HttpResponse<>(statusCodeValue, responseMessage, responseBody);
    }
}

