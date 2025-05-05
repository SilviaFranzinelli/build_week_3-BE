package it.epicode.EpicEnergyService.fatture;


import it.epicode.EpicEnergyService.enums.Stato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.List;

@Service
@Validated
public class FatturaService {

    @Autowired
    private FatturaRepository fatturaRepository;

    public List<FatturaResponse> getAllFatture() {
        List<Fattura> fatture = fatturaRepository.findAll();
        return fatture.stream()
                .map(fattura -> new FatturaResponse(
                        fattura.getNumero(),
                        fattura.getData(),
                        fattura.getImporto(),
                        fattura.getStato(),
                        fattura.getCliente().getId()
                ))
                .toList();
    }

    public List<FatturaResponse> getFattureByClienteId(Long clienteId) {
        List<Fattura> fatture = fatturaRepository.findByClienteId(clienteId);
        return fatture.stream()
                .map(fattura -> new FatturaResponse(
                        fattura.getNumero(),
                        fattura.getData(),
                        fattura.getImporto(),
                        fattura.getStato(),
                        fattura.getCliente().getId()
                ))
                .toList();
    }

    public List<FatturaResponse> getFattureByStato(Stato stato) {
        List<Fattura> fatture = fatturaRepository.findByStato(stato);
        return fatture.stream()
                .map(fattura -> new FatturaResponse(
                        fattura.getNumero(),
                        fattura.getData(),
                        fattura.getImporto(),
                        fattura.getStato(),
                        fattura.getCliente().getId()
                ))
                .toList();
    }

    public List<FatturaResponse> getFattureByData(LocalDate data) {
        List<Fattura> fatture = fatturaRepository.findByData(data);
        return fatture.stream()
                .map(fattura -> new FatturaResponse(
                        fattura.getNumero(),
                        fattura.getData(),
                        fattura.getImporto(),
                        fattura.getStato(),
                        fattura.getCliente().getId()
                ))
                .toList();
    }

    public List<FatturaResponse> getFattureByYear(int year) {
        List<Fattura> fatture = fatturaRepository.findByYear(year);
        return fatture.stream()
                .map(fattura -> new FatturaResponse(
                        fattura.getNumero(),
                        fattura.getData(),
                        fattura.getImporto(),
                        fattura.getStato(),
                        fattura.getCliente().getId()
                ))
                .toList();
    }

    public List<FatturaResponse> getFattureByImportoBetween(double min, double max) {
        List<Fattura> fatture = fatturaRepository.findByImportoBetween(min, max);
        return fatture.stream()
                .map(fattura -> new FatturaResponse(
                        fattura.getNumero(),
                        fattura.getData(),
                        fattura.getImporto(),
                        fattura.getStato(),
                        fattura.getCliente().getId()
                ))
                .toList();
    }


}
