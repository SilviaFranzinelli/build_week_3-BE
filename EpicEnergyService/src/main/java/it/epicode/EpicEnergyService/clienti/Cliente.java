package it.epicode.EpicEnergyService.clienti;

import it.epicode.EpicEnergyService.enums.TipoCliente;
import it.epicode.EpicEnergyService.fatture.Fattura;
import it.epicode.EpicEnergyService.fatture.FatturaRepository;
import it.epicode.EpicEnergyService.model.Indirizzo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "clienti")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, length = 100)
    private String ragioneSociale;
    @Column(nullable = false, length = 11)
    private String partitaIva;
    @Column(nullable = false, length = 100)
    private String email;
    @Column(nullable = false)
    private LocalDate dataInserimento;
    @Column(nullable = false)
    private LocalDate dataUltimoContatto;
    @Column(nullable = false)
    private double fatturatoAnnuale;
    @Column(nullable = false, length = 100)
    private String pec;
    @Column(nullable = false, length = 100)
    private String telefono;
    @Column(nullable = false, length = 100)
    private String emailContatto;
    @Column(nullable = false, length = 100)
    private String nomeContatto;
    @Column(nullable = false, length = 100)
    private String cognomeContatto;
    @Column(nullable = false, length = 100)
    private String telefonoContatto;

    private String logoAziendale;

    @Enumerated(EnumType.STRING)
    private TipoCliente tipoCliente;

    @OneToMany(mappedBy = "cliente")
    private List<Fattura> fatture = new ArrayList<>();

    @OneToOne
    private Indirizzo sedeOperativa;
    @OneToOne
    private Indirizzo sedeLegale;
}
