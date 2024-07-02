package com.trello.global;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@Builder
@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    @Setter
    private String message;
    private int errorCode;
    private HttpStatus statusCode;

    private List<Object> errors;

    @JsonIgnore
    private final Map<String, Object> errorDetail = new HashMap<>();

    @JsonIgnore
    private Object[] params;

    public ErrorResponse(String message, int errorCode, HttpStatus statusCode, Object... params) {
        this.message = message;
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.params = params;
        if (Objects.nonNull(params)) {
            setErrorDetail(params);
        }
    }

    public ErrorResponse setErrors(List<Object> errors) {
        this.errors = errors;
        return this;
    }

    public void setErrorDetail(Object... params) {
        for (Object param : params) {
            this.errorDetail.put("exception cause : ", String.valueOf(param));
        }
    }
}
