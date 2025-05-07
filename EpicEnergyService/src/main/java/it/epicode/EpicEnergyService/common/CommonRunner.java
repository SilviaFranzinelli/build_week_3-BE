package it.epicode.EpicEnergyService.common;

import com.github.javafaker.Faker;
import it.epicode.EpicEnergyService.clienti.Cliente;
import it.epicode.EpicEnergyService.clienti.ClienteRepository;
import it.epicode.EpicEnergyService.enums.TipoCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.time.LocalDate;

@Configuration
@Order(1)
public class CommonRunner implements CommandLineRunner {

    @Autowired
    private Faker faker;

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void run(String... args) throws Exception {

        if (clienteRepository.count() == 0){
            for( int i = 0; i < 10; i++) {
                Cliente cliente = new Cliente();
                cliente.setRagioneSociale(faker.company().name());
                cliente.setPartitaIva("IT" + faker.number().digits(9));
                cliente.setEmail(faker.internet().emailAddress());
                cliente.setPec(faker.internet().emailAddress());
                cliente.setTelefono(faker.phoneNumber().cellPhone());
                cliente.setEmailContatto(faker.internet().emailAddress());
                cliente.setNomeContatto(faker.name().firstName());
                cliente.setCognomeContatto(faker.name().lastName());
                cliente.setTelefonoContatto(faker.phoneNumber().cellPhone());
                cliente.setDataInserimento(LocalDate.now());
                cliente.setDataUltimoContatto(LocalDate.now());
                cliente.setFatturatoAnnuale((long) faker.number().numberBetween(100_000, 10_000_000));
                cliente.setLogoAziendale("logo");
                cliente.setTipoCliente(TipoCliente.PA);

                clienteRepository.save(cliente);
            }

        }

    }
}
