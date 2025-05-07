package it.epicode.EpicEnergyService.model;

import jakarta.persistence.*;

@Entity
public class Provincia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String sigla;

    public Provincia(String nome, String sigla) {
    }
}
