package it.epicode.EpicEnergyService.clienti;

import it.epicode.EpicEnergyService.enums.TipoCliente;
import it.epicode.EpicEnergyService.fatture.Fattura;
import it.epicode.EpicEnergyService.fatture.FatturaResponse;
import it.epicode.EpicEnergyService.model.Indirizzo;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponse {
    private Long id;
    private String ragioneSociale;
    private String partitaIva;
    private String email;
    private LocalDate dataInserimento;
    private LocalDate dataUltimoContatto;
    private double fatturatoAnnuale;
    private String pec;
    private String telefono;
    private String emailContatto;
    private String nomeContatto;
    private String cognomeContatto;
    private String telefonoContatto;
    private String logoAziendale;
    private TipoCliente tipoCliente;

    private List<FatturaResponse> fatture;

    private Indirizzo sedeLegale;
    private Indirizzo sedeOperativa;
}
