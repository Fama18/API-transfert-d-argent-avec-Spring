package com.Transfert.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Emetteur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String numCni;
    private String nom;
    private String prenom;
    private String telephone;
    @OneToMany(mappedBy = "emetteur", fetch = FetchType.LAZY)
    private List<Envoie> envoies = new ArrayList<Envoie>();
}
