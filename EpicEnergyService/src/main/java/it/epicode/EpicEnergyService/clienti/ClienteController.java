package it.epicode.EpicEnergyService.clienti;

import it.epicode.EpicEnergyService.common.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clienti")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping("")
    public Page<ClienteResponse> getClienti(@RequestParam(name = "page",defaultValue = "0") int page,
                                    @RequestParam (name = "size", defaultValue = "20") int size,
                                    @RequestParam( name = "sort", defaultValue = "id") String sortBy) {
        return clienteService.getClienti(page, size, sortBy);
    }

    @GetMapping("/{id}")
    public ClienteResponse getCliente(@PathVariable(name = "id") Long id) {
        return clienteService.findById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse save(@RequestBody ClienteRequest request) throws Exception {
        return clienteService.save(request);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable(name = "id") Long id, @RequestBody ClienteRequest cliente) throws Exception {
        clienteService.update(id, cliente);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(name = "id") Long id) throws Exception {
        clienteService.delete(id);
    }

    @GetMapping("/provincia")
    public Page<ClienteResponse> SortByProvincia(@RequestParam(name = "page", defaultValue = "0") int page,
                                                 @RequestParam (name = "size", defaultValue = "20") int size,
                                                 @RequestParam(name = "sort", defaultValue = "Provincia") String sortBy) {
        return clienteService.SortByProvincia(page, size, sortBy);
    }
}
