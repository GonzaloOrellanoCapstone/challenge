package com.tenpo.challenge.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "log_message")
public class LogMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonProperty("method")
    private String method;
    @JsonProperty("requestURI")
    private String requestURI;
    @JsonProperty("requestBody")
    private String requestBody;
    @JsonProperty("status")
    private int status;
    @JsonProperty("responseBody")
    private String responseBody;
    @JsonProperty("timeTaken")
    private Long timeTaken;

    public LogMessage(String method, String requestURI, String requestBody, int status, String responseBody, Long timeTaken) {
        this.method = method;
        this.requestURI = requestURI;
        this.requestBody = requestBody;
        this.status = status;
        this.responseBody = responseBody;
        this.timeTaken = timeTaken;
    }
}
