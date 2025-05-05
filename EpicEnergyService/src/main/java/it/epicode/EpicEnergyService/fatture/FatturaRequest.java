package it.epicode.EpicEnergyService.fatture;


import it.epicode.EpicEnergyService.enums.Stato;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FatturaRequest {

    @NotNull(message = "Inserire la data")
    private LocalDate data;

    @NotNull(message = "Inserire l'importo")
    private double importo;

    @NotNull(message = "Inserire lo stato")
    private Stato stato;

    @NotNull(message = "Inserire l'id del cliente")
    private Long clienteId;
}
