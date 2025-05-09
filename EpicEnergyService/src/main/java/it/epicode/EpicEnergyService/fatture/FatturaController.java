package it.epicode.EpicEnergyService.fatture;


import it.epicode.EpicEnergyService.common.CommonResponse;
import it.epicode.EpicEnergyService.enums.Stato;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/fatture")
@Validated
public class FatturaController {

    @Autowired
    private FatturaService fatturaService;

//    @GetMapping
//    public List<FatturaResponse> getAllFatture() {
//        return fatturaService.getAllFatture();
//    }
//
//    @GetMapping("/cliente/{id}")
//    public List<FatturaResponse> getFattureByClienteId(@RequestParam Long clienteId) {
//        return fatturaService.getFattureByClienteId(clienteId);
//    }
//
//    @GetMapping("/stato")
//    public List<FatturaResponse> getFattureByStato(@RequestParam Stato stato) {
//        return fatturaService.getFattureByStato(stato);
//    }
//
//    @GetMapping("/data")
//    public List<FatturaResponse> getFattureByData(@RequestParam LocalDate data) {
//        return fatturaService.getFattureByData(data);
//    }
//
//    @GetMapping("/anno")
//    public List<FatturaResponse> getFattureByYear(@RequestParam int year) {
//        return fatturaService.getFattureByYear(year);
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public CommonResponse saveFattura(@RequestBody @Valid FatturaRequest request){
        return fatturaService.saveFattura(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteFattura(@PathVariable(name = "id") Long id){
        fatturaService.deleteFattura(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void updateFattura(@PathVariable(name = "id") Long id, @RequestBody @Valid FatturaRequest request){
        fatturaService.updateFattura(id, request);
    }

//    @GetMapping
//    public Page<FatturaResponse> getAllFatture(@RequestParam(defaultValue = "0") int page,
//                                               @RequestParam(defaultValue = "10") int size) {
//        return fatturaService.getAllFatture(page, size);
//    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public FatturaResponse findFatturaById(@PathVariable(name = "id") Long id) {
        return fatturaService.findFatturaById(id);
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public Page<FatturaResponse> getAllFattureSort(@RequestParam(name = "page", defaultValue = "0") int page,
                                               @RequestParam(name = "size", defaultValue = "10") int size,
                                               @RequestParam(name = "sort", defaultValue = "numero") String sort) {
        return fatturaService.getAllFattureSort(page, size, sort);
    }

    @GetMapping("/order-by-year")
    @PreAuthorize("isAuthenticated()")
    public Page<FatturaResponse> getAllFattureOrderByYear(@RequestParam(name = "page", defaultValue = "0") int page,
                                                           @RequestParam(name = "size", defaultValue = "10") int size) {
        return fatturaService.getAllFattureOrderByYear(page, size);
    }

    @GetMapping("/importo/range")
    @PreAuthorize("isAuthenticated()")
    public Page<FatturaResponse> getAllFattureByImportoBetween(@RequestParam("min") double min,
                                                                @RequestParam("max") double max,
                                                                @RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "10") int size) {
        return fatturaService.getAllFattureByImportoBetween(min, max, page, size);
    }

    @GetMapping("/stato")
    @PreAuthorize("isAuthenticated()")
    public Page<FatturaResponse> getAllFattureByStato(@RequestParam(name = "stato" ) Stato stato,
                                                      @RequestParam(name = "page" ,defaultValue = "0") int page,
                                                      @RequestParam(name = "size", defaultValue = "10") int size) {
        return fatturaService.getAllFattureByStato(stato, page, size);
    }

    @GetMapping("/totale-fatturato/{clienteId}")
    @PreAuthorize("isAuthenticated()")
    public double getTotaleFatturatoByClienteId(@PathVariable(name = "clienteId") Long clienteId) {
        return fatturaService.getTotaleFatturatoByClienteId(clienteId);
    }
}
