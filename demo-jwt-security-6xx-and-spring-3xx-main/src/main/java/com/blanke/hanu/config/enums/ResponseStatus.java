package com.blanke.hanu.config.enums;
import lombok.*;
import org.springframework.http.HttpStatus;
@AllArgsConstructor
@Getter
@Setter
public final class ResponseStatus {
    private String code;
    private String message;
    private HttpStatus httpCode;
}
