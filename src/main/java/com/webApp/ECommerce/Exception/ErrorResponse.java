package com.webApp.ECommerce.Exception;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorResponse {
    private Date data = new Date();
    private int code;
    private String message;
}
