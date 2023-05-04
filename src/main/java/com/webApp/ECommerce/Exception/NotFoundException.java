package com.webApp.ECommerce.Exception;

import lombok.Data;

@Data
public class NotFoundException extends Exception{
    private String message;

    public NotFoundException() {
        super();
    }

    public NotFoundException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessaggio() {
        return message;
    }

    public void setMessaggio(String messaggio) {
        this.message = messaggio;
    }
}
