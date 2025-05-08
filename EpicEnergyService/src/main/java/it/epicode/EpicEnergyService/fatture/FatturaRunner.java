package it.epicode.EpicEnergyService.fatture;

import it.epicode.EpicEnergyService.clienti.ClienteRepository;
import it.epicode.EpicEnergyService.enums.Stato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class FatturaRunner implements CommandLineRunner {

    @Autowired
    private FatturaRepository fatturaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void run(String... args) throws Exception {
        Fattura fattura = new Fattura();
        fattura.setData(LocalDate.now().minusDays(10));
        fattura.setImporto(100.0);
        fattura.setStato(Stato.INVIATO);
        fattura.setCliente(clienteRepository.findById(1L).get());
        fatturaRepository.save(fattura);

        Fattura fattura1 = new Fattura();
        fattura1.setData(LocalDate.now().minusDays(5));
        fattura1.setImporto(200.0);
        fattura1.setStato(Stato.RIFIUTATO);
        fattura1.setCliente(clienteRepository.findById(2L).get());
        fatturaRepository.save(fattura1);

    }
}
