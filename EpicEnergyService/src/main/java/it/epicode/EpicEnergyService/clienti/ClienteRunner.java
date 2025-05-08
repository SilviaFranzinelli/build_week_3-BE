package it.epicode.EpicEnergyService.clienti;

import it.epicode.EpicEnergyService.controller.ImportController;
import it.epicode.EpicEnergyService.model.Comune;
import it.epicode.EpicEnergyService.model.Indirizzo;
import it.epicode.EpicEnergyService.model.IndirizzoService;
import it.epicode.EpicEnergyService.repository.ComuneRepository;
import it.epicode.EpicEnergyService.repository.IndirizzoRepository;
import it.epicode.EpicEnergyService.service.CSVImportService;
import jakarta.persistence.ManyToOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static it.epicode.EpicEnergyService.enums.TipoCliente.PA;
import static it.epicode.EpicEnergyService.enums.TipoCliente.SPA;

@Component
@Order(1)
public class ClienteRunner implements CommandLineRunner {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private IndirizzoRepository indirizzoRepository;
    @Autowired
    IndirizzoService indirizzoService;
    @Autowired
    CSVImportService csvImportService;
    @Autowired
    private ImportController importController;
    @Autowired
    private ComuneRepository comuneRepository;


    @Override
    public void run(String... args) throws Exception {

        csvImportService.importaProvinceDaCSV("EpicEnergyService/src/main/resources/assets/province-italiane.csv");
        csvImportService.importaComuniDaCSV("EpicEnergyService/src/main/resources/assets/comuni-italiani.csv");

        Indirizzo indirizzo1 = new Indirizzo();
        indirizzo1.setVia("Via Roma");
        indirizzo1.setCivico("12");
        indirizzo1.setComune(comuneRepository.findById(2L).get());
        indirizzoRepository.save(indirizzo1);

        Indirizzo indirizzo2 = new Indirizzo();
        indirizzo2.setVia("Via Milano");
        indirizzo2.setCivico("34");
        indirizzo2.setComune(comuneRepository.findById(329L).get());
        indirizzoRepository.save(indirizzo2);

        Cliente cliente1 = new Cliente();
        cliente1.setRagioneSociale("Epic Energy");
        cliente1.setPartitaIva("12345678901");
        cliente1.setEmail("cM8t2@example.com");
        cliente1.setDataInserimento(LocalDate.now());
        cliente1.setDataUltimoContatto(LocalDate.now());
        cliente1.setFatturatoAnnuale(45.5);
        cliente1.setPec("cM8t2@example.com");
        cliente1.setTelefono("1234567890");
        cliente1.setEmailContatto("cM8t2@example.com");
        cliente1.setNomeContatto("Zohn");
        cliente1.setCognomeContatto("Doe");
        cliente1.setTelefonoContatto("1234567890");
        cliente1.setLogoAziendale("logo.png");
        cliente1.setTipoCliente(PA);
        cliente1.setSedeLegale(indirizzo2);
        clienteRepository.save(cliente1);
        System.out.println("------------------------------------------");
        System.out.println("Cliente salvato con id: " + cliente1.getId());

        Cliente cliente2 = new Cliente();
        cliente2.setRagioneSociale("Pizzeria Mamma Mia");
        cliente2.setPartitaIva("12345678909");
        cliente2.setEmail("pizza@example.com");
        cliente2.setDataInserimento(LocalDate.of(2023, 1, 1));
        cliente2.setDataUltimoContatto(LocalDate.of(2024, 1, 1));
        cliente2.setFatturatoAnnuale(700000.0);
        cliente2.setPec("pizza@example.com");
        cliente2.setTelefono("1234567890");
        cliente2.setEmailContatto("pizza@example.com");
        cliente2.setNomeContatto("Mario");
        cliente2.setCognomeContatto("Sburroni");
        cliente2.setTelefonoContatto("1234567890");
        cliente2.setLogoAziendale("logo.png");
        cliente2.setTipoCliente(SPA);
        cliente2.setSedeLegale(indirizzo1);
        clienteRepository.save(cliente2);
        System.out.println("------------------------------------------");
        System.out.println("Cliente salvato con id: " + cliente2.getId());
    }
}
