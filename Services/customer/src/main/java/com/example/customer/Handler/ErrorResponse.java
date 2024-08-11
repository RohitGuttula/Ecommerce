package com.example.customer.Handler;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Date;
@Data
public class ErrorResponse {
    private HttpStatus status;
    private LocalDateTime localDateTime;
    private String errorCode;
    private String errorMessage;
    public ErrorResponse(String errorCode,String errorMessage){
        this.errorCode=errorCode;
        this.errorMessage=errorMessage;
    }
}
