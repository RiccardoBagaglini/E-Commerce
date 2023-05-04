package com.webApp.ECommerce.Exception;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class InfoMsg {

    public LocalDate data;
    public String message;

}
