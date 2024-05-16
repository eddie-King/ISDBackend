package com.blanke.hanu.config.Common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseModel {

    protected Integer code;
    protected HttpStatus statusCode;
    protected String message;
    protected Object content;
    protected Long totalRecords;

    public ResponseModel(Integer code, HttpStatus statusCode, String message, Object content) {
        this.code = code;
        this.statusCode = statusCode;
        this.message = message;
        this.content = content;
    }
}