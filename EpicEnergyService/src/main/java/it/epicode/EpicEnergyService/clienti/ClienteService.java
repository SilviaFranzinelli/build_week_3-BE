package it.epicode.EpicEnergyService.clienti;

import it.epicode.EpicEnergyService.exceptions.ResourceNotFoundException;
import it.epicode.EpicEnergyService.fatture.Fattura;
import it.epicode.EpicEnergyService.fatture.FatturaRepository;
import it.epicode.EpicEnergyService.fatture.FatturaResponse;
import it.epicode.EpicEnergyService.model.Indirizzo;
import it.epicode.EpicEnergyService.repository.IndirizzoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
@RequiredArgsConstructor
public class ClienteService {
    @Autowired
    private final ClienteRepository clienteRepository;

    @Autowired
    private FatturaRepository fatturaRepository;

    @Autowired
    private IndirizzoRepository indirizzoRepository;

    public Page<ClienteResponse> getClienti(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<Cliente> clienti = clienteRepository.findAll(pageable);

        return clienti.map(cliente -> new ClienteResponse(
                cliente.getId(),
                cliente.getRagioneSociale(),
                cliente.getPartitaIva(),
                cliente.getEmail(),
                cliente.getDataInserimento(),
                cliente.getDataUltimoContatto(),
                cliente.getFatturatoAnnuale(),
                cliente.getPec(),
                cliente.getTelefono(),
                cliente.getEmailContatto(),
                cliente.getNomeContatto(),
                cliente.getCognomeContatto(),
                cliente.getTelefonoContatto(),
                cliente.getLogoAziendale(),
                cliente.getTipoCliente(),
                cliente.getFatture().stream()
                        .map(fattura -> new FatturaResponse(
                                fattura.getNumero(),
                                fattura.getData(),
                                fattura.getImporto(),
                                fattura.getStato(),
                                fattura.getCliente().getId()))
                        .toList(),
                cliente.getSedeLegale(),
                cliente.getSedeOperativa()
                ));
    }

    public Cliente findById(Long id) {
        return clienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Non esiste nessun cliente con id: " + id));
    }

    public ClienteResponse findByIdResponse(Long id) {
        Cliente cliente = clienteRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Non esiste nessun cliente con id: " + id));

        return new ClienteResponse(
                cliente.getId(),
                cliente.getRagioneSociale(),
                cliente.getPartitaIva(),
                cliente.getEmail(),
                cliente.getDataInserimento(),
                cliente.getDataUltimoContatto(),
                cliente.getFatturatoAnnuale(),
                cliente.getPec(),
                cliente.getTelefono(),
                cliente.getEmailContatto(),
                cliente.getNomeContatto(),
                cliente.getCognomeContatto(),
                cliente.getTelefonoContatto(),
                cliente.getLogoAziendale(),
                cliente.getTipoCliente(),
                cliente.getFatture().stream()
                        .map(fattura -> new FatturaResponse(
                                fattura.getNumero(),
                                fattura.getData(),
                                fattura.getImporto(),
                                fattura.getStato(),
                                fattura.getCliente().getId()))
                        .toList(),
                cliente.getSedeLegale(),
                cliente.getSedeOperativa()
        );
    }

    public ClienteResponse save(ClienteRequest request) {
        Cliente cliente = new Cliente();
        cliente.setRagioneSociale(request.getRagioneSociale());
        cliente.setPartitaIva(request.getPartitaIva());
        cliente.setEmail(request.getEmail());
        cliente.setDataInserimento(request.getDataInserimento());
        cliente.setDataUltimoContatto(request.getDataUltimoContatto());
        cliente.setFatturatoAnnuale(request.getFatturatoAnnuale());
        cliente.setPec(request.getPec());
        cliente.setTelefono(request.getTelefono());
        cliente.setEmailContatto(request.getEmailContatto());
        cliente.setNomeContatto(request.getNomeContatto());
        cliente.setCognomeContatto(request.getCognomeContatto());
        cliente.setTelefonoContatto(request.getTelefonoContatto());
        cliente.setLogoAziendale(request.getLogoAziendale());
        cliente.setTipoCliente(request.getTipoCliente());


        List<Fattura> fatture = request.getFattureId().stream().map(id -> fatturaRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Non esiste nessuna fattura con id: " + id))).toList();
        cliente.setFatture(fatture);

        Indirizzo sedeLegale = indirizzoRepository.findById(request
                .getSedeLegaleId())
                .orElseThrow(() -> new ResourceNotFoundException("Non esiste nessun indirizzo con id: " + request.getSedeLegaleId()));

        cliente.setSedeLegale(sedeLegale);

        Indirizzo sedeOperativa = indirizzoRepository.findById(request
                .getSedeOperativaId())
                .orElse(null);

        cliente.setSedeOperativa(sedeOperativa);

        Cliente clienteSalvato = clienteRepository.save(cliente);

        ClienteResponse response = new ClienteResponse();
        response.setId(clienteSalvato.getId());
        response.setRagioneSociale(clienteSalvato.getRagioneSociale());
        response.setPartitaIva(clienteSalvato.getPartitaIva());
        response.setEmail(clienteSalvato.getEmail());
        response.setDataInserimento(clienteSalvato.getDataInserimento());
        response.setDataUltimoContatto(clienteSalvato.getDataUltimoContatto());
        response.setFatturatoAnnuale(clienteSalvato.getFatturatoAnnuale());
        response.setPec(clienteSalvato.getPec());
        response.setTelefono(clienteSalvato.getTelefono());
        response.setEmailContatto(clienteSalvato.getEmailContatto());
        response.setNomeContatto(clienteSalvato.getNomeContatto());
        response.setCognomeContatto(clienteSalvato.getCognomeContatto());
        response.setTelefonoContatto(clienteSalvato.getTelefonoContatto());
        response.setLogoAziendale(clienteSalvato.getLogoAziendale());
        response.setTipoCliente(clienteSalvato.getTipoCliente());

        response.setFatture(clienteSalvato.getFatture().stream()
                .map(f -> new FatturaResponse(
                f.getNumero(),
                f.getData(),
                f.getImporto(),
                f.getStato(),
                f.getCliente().getId()))
                .toList()
        );

        response.setSedeLegale(clienteSalvato.getSedeLegale());
        response.setSedeOperativa(clienteSalvato.getSedeOperativa());

        return response;

    }

    public void delete(Long id) {
        Cliente found = findById(id);
        clienteRepository.delete(found);
    }

    public Cliente update(Long id, Cliente cliente) {
        Cliente found = findById(id);
        BeanUtils.copyProperties(cliente, found, "id");
        return clienteRepository.save(found);
    }
}
