package it.epicode.EpicEnergyService.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "indirizzi")
public class Indirizzo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String via;
    private String civico;

    @ManyToOne
    private Comune comune;
}

