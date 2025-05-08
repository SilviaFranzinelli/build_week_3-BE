package it.epicode.EpicEnergyService.clienti;

import it.epicode.EpicEnergyService.common.CommonResponse;
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

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
                cliente.getFatture() !=null ? cliente.getFatture().stream()
                        .map(fattura -> new FatturaResponse(
                                fattura.getNumero(),
                                fattura.getData(),
                                fattura.getImporto(),
                                fattura.getStato(),
                                fattura.getCliente().getId()))
                        .collect(Collectors.toList()) : Collections.emptyList(),
                cliente.getSedeLegale(),
                cliente.getSedeOperativa()
                ));
    }

    public ClienteResponse findById(Long id) {
        Cliente cliente = clienteRepository.findById(id)
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
    public CommonResponse save(ClienteRequest request) {
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

        return new CommonResponse(response.getId());

    }

    public void delete(Long id) {

        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Non esiste nessun cliente con id: " + id));

        clienteRepository.delete(cliente);
    }

    public void update(Long id, ClienteRequest cliente) {
        Cliente cliente1 = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Non esiste nessun cliente con id: " + id));

        BeanUtils.copyProperties(cliente, cliente1);
        clienteRepository.save(cliente1);
    }

    public Page<ClienteResponse> SortByProvincia(int page, int size, String sortBy) {



        Pageable pageable = PageRequest.of(0, 20, Sort.by("sedeLegale.comune.provincia"));

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

    public Page<ClienteResponse> findAllByFatturatoAnnuale(int page, int size, double fatturatoAnnuale) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Cliente> clienti = clienteRepository.findAllByFatturatoAnnuale(fatturatoAnnuale, pageable);

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
    public Page<ClienteResponse> findAllByDataInserimento(int page, int size, LocalDate dataInserimento) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Cliente> clienti = clienteRepository.findAllByDataInserimento(dataInserimento, pageable);

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

    public Page<ClienteResponse> findAllByDataUltimoContatto(int page, int size, LocalDate dataInserimento) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Cliente> clienti = clienteRepository.findAllByDataUltimoContatto(dataInserimento, pageable);

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

    public Page<ClienteResponse> findNomeContattoLike(int page, int size, String nomeContatto){

        Pageable pageable = PageRequest.of(page, size);
        Page<Cliente> clienti = clienteRepository.findNomeContattoLike(nomeContatto, pageable);

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
}
