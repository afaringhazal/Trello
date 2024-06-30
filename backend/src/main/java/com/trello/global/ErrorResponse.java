package com.trello.global;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@Builder
@Getter
@NoArgsConstructor
public class ErrorResponse {

    @Setter
    private String message;
    private String errorCode;
    private HttpStatus statusCode;
    @JsonIgnore
    private final Map<String, Object> errorDetail = new HashMap<>();
    @JsonIgnore
    private Object[] params;

    public ErrorResponse(String message, String errorCode, HttpStatus statusCode,
                         Object... params) {
        this.message = message;
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.params = params;
        if (Objects.nonNull(params)) {
            setErrorDetail(params);
        }
    }

    public void setErrorDetail(Object... params) {
        for (Object param : params) {
            this.errorDetail.put("exception cause : ", String.valueOf(param));
        }
    }


}