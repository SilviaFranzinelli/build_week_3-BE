package it.epicode.EpicEnergyService.clienti;

import it.epicode.EpicEnergyService.common.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/clienti")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public Page<ClienteResponse> getClienti(@RequestParam(name = "page",defaultValue = "0") int page,
                                    @RequestParam (name = "size", defaultValue = "20") int size,
                                    @RequestParam( name = "sort", defaultValue = "id") String sortBy) {
        return clienteService.getClienti(page, size, sortBy);
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ClienteResponse getCliente(@PathVariable(name = "id") Long id) {
        return clienteService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public CommonResponse save(@RequestBody ClienteRequest request) throws Exception {
        return clienteService.save(request);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void update(@PathVariable(name = "id") Long id, @RequestBody ClienteRequest cliente) throws Exception {
        clienteService.update(id, cliente);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable(name = "id") Long id) throws Exception {
        clienteService.delete(id);
    }

    @GetMapping("/provincia")
    @PreAuthorize("isAuthenticated()")
    public Page<ClienteResponse> SortByProvincia(@RequestParam(name = "page", defaultValue = "0") int page,
                                                 @RequestParam (name = "size", defaultValue = "20") int size,
                                                 @RequestParam(name = "provincia", defaultValue = "Torino") String sortBy) {
        return clienteService.SortByProvincia(page, size, sortBy);
    }

    @GetMapping("/fatturato")
    @PreAuthorize("hasRole('ADMIN')")
    public Page<ClienteResponse> findAllByFatturatoAnnuale(@RequestParam(name = "page", defaultValue = "0") int page,
                                                           @RequestParam(name = "size", defaultValue = "20") int size,
                                                           @RequestParam(name = "fatturatoAnnuale", defaultValue = "0") double fatturatoAnnuale) {
        return clienteService.findAllByFatturatoAnnuale(page, size, fatturatoAnnuale);
    }

    @GetMapping("/data-inserimento")
    @PreAuthorize("isAuthenticated()")
    public Page<ClienteResponse> findAllByDataInserimento(@RequestParam(name = "page", defaultValue = "0") int page,
                                                          @RequestParam(name = "size", defaultValue = "20") int size,
                                                          @RequestParam(name = "dataInserimento") LocalDate dataInserimento) {
        return clienteService.findAllByDataInserimento(page, size, dataInserimento);
    }

    @GetMapping("/data-ultimo-contatto")
    @PreAuthorize("isAuthenticated()")
    public Page<ClienteResponse> findAllByDataUltimoContatto(@RequestParam(name = "page", defaultValue = "0") int page,
                                                             @RequestParam(name = "size", defaultValue = "20") int size,
                                                             @RequestParam(name = "dataUltimoContatto") LocalDate dataUltimoContatto) {
        return clienteService.findAllByDataUltimoContatto(page, size, dataUltimoContatto);
    }

    @GetMapping("/nomeContatto")
    @PreAuthorize("isAuthenticated()")
    public Page<ClienteResponse> findNomeContattoLike(@RequestParam(name = "page", defaultValue = "0") int page,
                                                       @RequestParam(name = "size", defaultValue = "20") int size,
                                                       @RequestParam(name = "nomeContatto") String nomeContatto) {
        return clienteService.findNomeContattoLike(page, size, nomeContatto);
    }
}
