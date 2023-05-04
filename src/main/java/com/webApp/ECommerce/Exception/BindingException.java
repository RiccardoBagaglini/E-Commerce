package com.webApp.ECommerce.Exception;

public class BindingException extends Exception{
    private String message;

    public BindingException()
    {
        super();
    }

    public BindingException(String messaggio)
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
