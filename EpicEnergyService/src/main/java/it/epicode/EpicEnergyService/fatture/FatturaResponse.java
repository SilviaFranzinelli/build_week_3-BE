package it.epicode.EpicEnergyService.fatture;


import it.epicode.EpicEnergyService.enums.Stato;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FatturaResponse {

    private Long numero;
    private LocalDate data;
    private double importo;
    private Stato stato;
    private Long clienteId;
}
