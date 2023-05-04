package com.webApp.ECommerce.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticoliDto {
    private Integer idArticolo;
    private Integer codiceArticolo;
    private String nomeArticolo;
    private Float prezzoArticolo;
    private Integer quantita;
    private Boolean disponibilita;
}
