package com.Transfert.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Envoie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String dateEnvoie;
    private Double montant;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Emetteur emetteur ;
}
