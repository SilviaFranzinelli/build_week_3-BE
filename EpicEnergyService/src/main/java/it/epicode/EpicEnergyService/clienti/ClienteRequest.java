package it.epicode.EpicEnergyService.clienti;

import it.epicode.EpicEnergyService.enums.TipoCliente;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
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
    private Long fatturatoAnnuale;
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

//    private Indirizzo sedeLegale;
//    private Indirizzo sedeOperativa;
}
