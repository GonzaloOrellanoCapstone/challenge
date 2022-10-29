package com.tenpo.challenge.security.service;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.tenpo.challenge.payload.response.CalculatorServiceResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

@Service
@Slf4j
public class CalculatorService {

    private static final HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
    private static final String URL_EXTERNAL_SERVICE = "http://localhost:8089/calculator/percentage";

    @CircuitBreaker(name = "calculatorservice", fallbackMethod = "fallback")
    public Double getExternalService(int firstValue, int secondValue) {
        try {
            final CalculatorServiceResponse response = new Gson().fromJson(callExternalServiceAPI(firstValue, secondValue), CalculatorServiceResponse.class);

            return Optional.ofNullable(response)
                    .orElse(new CalculatorServiceResponse())
                    .getPercentage();
        } catch (JsonSyntaxException e) {
            log.info("Unable to parse response");
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to parse response");
        }
    }

    private String callExternalServiceAPI(int firstValue, int secondValue) {
        final HttpRequest request = HttpRequest.newBuilder(URI.create(URL_EXTERNAL_SERVICE + "/" + firstValue + "/" + secondValue))
                .GET()
                .build();
        final HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            log.info("CalculatorService out of Service");
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "CalculatorService out of Service");
        }

        return response.body();
    }

    private Double fallback(final Throwable t) {
        log.info("Fallback cause, {}", t.toString());
        return 200d;
    }

}
