package it.epicode.EpicEnergyService.model;
import jakarta.persistence.*;

@Entity
public class Comune {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToOne
    private Provincia provincia;

    public Comune(String nomeComune, Provincia provincia) {
    }
}
