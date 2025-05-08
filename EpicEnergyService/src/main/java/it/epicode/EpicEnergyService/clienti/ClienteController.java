package it.epicode.EpicEnergyService.clienti;

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
    public Page<Cliente> getClienti(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam (defaultValue = "20") int size,
                                    @RequestParam(defaultValue = "id") String sortBy) {
        return clienteService.getClienti(page, size, sortBy);
    }

    @GetMapping("/{id}")
    public Cliente getCliente(@PathVariable(name = "id") Long id) {
        return clienteService.findById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save(@RequestBody Cliente cliente) throws Exception {
        return clienteService.save(cliente);
    }

    @PutMapping("/{id}")
    public Cliente update(@PathVariable(name = "id") Long id, @RequestBody Cliente cliente) throws Exception {
        return clienteService.update(id, cliente);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(name = "id") Long id) throws Exception {
        clienteService.delete(id);
    }
}
