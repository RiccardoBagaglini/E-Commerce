package com.webApp.ECommerce.Exception;

public class DuplicateException extends Exception {

    private String message;

    public DuplicateException()
    {
        super();
    }

    public DuplicateException(String messaggio)
    {
        super(messaggio);
        this.message = messaggio;
    }

    public String getMessaggio()
    {
        return message;
    }

    public void setMessaggio(String messaggio)
    {
        this.message = messaggio;
    }
}
