package com.webApp.ECommerce.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="articolo")
public class Articoli implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_articolo",nullable = false)
    private Integer idArticolo;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="codice_articolo",unique = true, nullable = false,length = 50)
    private Integer codiceArticolo;

    @Column(name="nome_articolo",nullable = false,length = 255)
    private String nomeArticolo;

    @Column(name="prezzo_articolo",nullable = false, precision = 0)
    private Float prezzoArticolo;

    @Column(name="quantita",nullable = false,precision = 0)
    private Integer quantita;

    @Column(name="disponibilita",nullable = false)
    private Boolean disponibilita;


}
