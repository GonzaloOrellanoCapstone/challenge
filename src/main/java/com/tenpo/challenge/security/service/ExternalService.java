package com.tenpo.challenge.security.service;

import com.google.gson.JsonSyntaxException;
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
import java.util.Random;

@Service
@Slf4j
public class ExternalService {

    private static final HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
    private static final String URL_EXTERNAL_SERVICE = "http://mock-url";

    @CircuitBreaker(name = "externalservice", fallbackMethod = "fallback")
    public Double getExternalService(int firstValue, int secondValue) {
        try {
            /*
            final WeatherResponse response = new Gson().fromJson(callExternalServiceAPI(), WeatherResponse.class);

            return Optional.ofNullable(response)
                    .orElse(new WeatherResponse())
                    .getData()
                    .stream()
                    .findFirst()
                    .map(WeatherItem::getTemp)
                    .orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "There is no data available"));
             */
            Double addValues = Double.valueOf(firstValue + secondValue);

            return addValues + calculatePercentage(addValues, randomNumber());
        } catch (JsonSyntaxException e) {
            log.info("Unable to parse response");
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to parse response");
        }
    }

    private String callExternalServiceAPI() {
        final HttpRequest request = HttpRequest.newBuilder(URI.create(URL_EXTERNAL_SERVICE))
                .GET()
                .build();
        final HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            log.info("ExternalService out of Service");
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "ExternalService out of Service");
        }

        return response.body();
    }

    private Double fallback(final Throwable t) {
        log.info("Fallback cause, {}", t.toString());
        return 200d;
    }

    private int randomNumber() {
        Random randI = new Random();
        return randI.nextInt(100) + 1;
    }

    private double calculatePercentage(double obtained, double total) {
        return obtained * 100 / total;
    }
}
