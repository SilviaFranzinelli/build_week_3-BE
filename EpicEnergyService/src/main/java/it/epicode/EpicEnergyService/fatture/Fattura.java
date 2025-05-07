package it.epicode.EpicEnergyService.fatture;

import it.epicode.EpicEnergyService.clienti.Cliente;
import it.epicode.EpicEnergyService.enums.Stato;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "fatture")
public class Fattura {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long numero;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false)
    private double importo;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Stato stato;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

}
