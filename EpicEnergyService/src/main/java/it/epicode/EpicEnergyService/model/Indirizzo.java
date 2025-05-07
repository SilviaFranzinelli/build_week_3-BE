package it.epicode.EpicEnergyService.model;
import jakarta.persistence.*;

@Entity
public class Indirizzo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String via;
    private String civico;
    private String localita;
    private String cap;

    @ManyToOne
    private Comune comune;
}

