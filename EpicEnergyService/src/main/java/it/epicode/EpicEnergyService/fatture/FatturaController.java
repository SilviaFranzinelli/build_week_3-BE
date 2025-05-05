package it.epicode.EpicEnergyService.fatture;


import it.epicode.EpicEnergyService.enums.Stato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/fatture")
public class FatturaController {

    @Autowired
    private FatturaService fatturaService;

    @GetMapping
    public List<FatturaResponse> getAllFatture() {
        return fatturaService.getAllFatture();
    }

    @GetMapping("/cliente/{id}")
    public List<FatturaResponse> getFattureByClienteId(@PathVariable Long clienteId) {
        return fatturaService.getFattureByClienteId(clienteId);
    }

    @GetMapping("/stato")
    public List<FatturaResponse> getFattureByStato(@RequestParam Stato stato) {
        return fatturaService.getFattureByStato(stato);
    }

    @GetMapping("/data")
    public List<FatturaResponse> getFattureByData(@PathVariable LocalDate data) {
        return fatturaService.getFattureByData(data);
    }

    @GetMapping("/anno")
    public List<FatturaResponse> getFattureByYear(@PathVariable int year) {
        return fatturaService.getFattureByYear(year);
    }
}
