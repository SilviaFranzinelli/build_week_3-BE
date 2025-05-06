package it.epicode.EpicEnergyService.clienti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static it.epicode.EpicEnergyService.enums.TipoCliente.PA;
import static it.epicode.EpicEnergyService.enums.TipoCliente.SPA;

@Component
public class ClienteRunner implements CommandLineRunner {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ClienteService clienteService;

    @Override
    public void run(String... args) throws Exception {
        Cliente cliente1 = new Cliente();
        cliente1.setRagioneSociale("Epic Energy");
        cliente1.setPartitaIva("12345678901");
        cliente1.setEmail("cM8t2@example.com");
        cliente1.setDataInserimento(LocalDate.now());
        cliente1.setDataUltimoContatto(LocalDate.now());
        cliente1.setFatturatoAnnuale(700000L);
        cliente1.setPec("cM8t2@example.com");
        cliente1.setTelefono("1234567890");
        cliente1.setEmailContatto("cM8t2@example.com");
        cliente1.setNomeContatto("Zohn");
        cliente1.setCognomeContatto("Doe");
        cliente1.setTelefonoContatto("1234567890");
        cliente1.setLogoAziendale("logo.png");
        cliente1.setTipoCliente(PA);
        clienteService.save(cliente1);
        System.out.println("------------------------------------------");
        System.out.println("Cliente salvato con id: " + cliente1.getId());

        Cliente cliente2 = new Cliente();
        cliente2.setRagioneSociale("Pizzeria Mamma Mia");
        cliente2.setPartitaIva("12345678909");
        cliente2.setEmail("pizza@example.com");
        cliente2.setDataInserimento(LocalDate.of(2023, 1, 1));
        cliente2.setDataUltimoContatto(LocalDate.of(2024, 1, 1));
        cliente2.setFatturatoAnnuale(100000L);
        cliente2.setPec("pizza@example.com");
        cliente2.setTelefono("1234567890");
        cliente2.setEmailContatto("pizza@example.com");
        cliente2.setNomeContatto("Mario");
        cliente2.setCognomeContatto("Sburroni");
        cliente2.setTelefonoContatto("1234567890");
        cliente2.setLogoAziendale("logo.png");
        cliente2.setTipoCliente(SPA);
        clienteService.save(cliente2);
        System.out.println("------------------------------------------");
        System.out.println("Cliente salvato con id: " + cliente2.getId());
    }
}
