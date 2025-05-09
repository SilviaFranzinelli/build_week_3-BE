package it.epicode.EpicEnergyService.clienti;

import it.epicode.EpicEnergyService.enums.TipoCliente;
import it.epicode.EpicEnergyService.fatture.FatturaResponse;
import it.epicode.EpicEnergyService.model.Indirizzo;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRequest {
    @NotBlank
    private String ragioneSociale;
    @NotBlank
    private String partitaIva;
    @Email
    private String email;
    @PastOrPresent
    private LocalDate dataInserimento;
    @PastOrPresent
    private LocalDate dataUltimoContatto;
    @PositiveOrZero
    private double fatturatoAnnuale;
    @Email
    private String pec;
    @NotBlank
    private String telefono;
    @Email
    private String emailContatto;
    @NotBlank
    private String nomeContatto;
    @NotBlank
    private String cognomeContatto;
    @NotBlank
    private String telefonoContatto;

    private String logoAziendale;
    @NotNull
    private TipoCliente tipoCliente;

    private List<Long> fattureId = new ArrayList<>();

    @NotNull
    private Long sedeLegaleId;
    private Long sedeOperativaId;
}
