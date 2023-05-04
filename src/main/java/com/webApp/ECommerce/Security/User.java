package com.webApp.ECommerce.Security;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private String idUser;
    private String email;
    private String password;
    private String abilitato;
    private List<Ruoli> ruolo;
}
