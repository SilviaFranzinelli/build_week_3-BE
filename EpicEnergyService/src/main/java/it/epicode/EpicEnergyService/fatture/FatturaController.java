package it.epicode.EpicEnergyService.fatture;


import it.epicode.EpicEnergyService.common.CommonResponse;
import it.epicode.EpicEnergyService.enums.Stato;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/fatture")
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
    public CommonResponse saveFattura(@RequestBody @Valid FatturaRequest request){
        return fatturaService.saveFattura(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFattura(@PathVariable Long id){
        fatturaService.deleteFattura(id);
    }

    @PutMapping("/{id}")
    public void updateFattura(@PathVariable Long id, @RequestBody @Valid FatturaRequest request){
        fatturaService.updateFattura(id, request);
    }

//    @GetMapping
//    public Page<FatturaResponse> getAllFatture(@RequestParam(defaultValue = "0") int page,
//                                               @RequestParam(defaultValue = "10") int size) {
//        return fatturaService.getAllFatture(page, size);
//    }

    @GetMapping("/{id}")
    public FatturaResponse findFatturaById(@PathVariable Long id) {
        return fatturaService.findFatturaById(id);
    }

    @GetMapping
    public Page<FatturaResponse> getAllFattureSort(@RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "10") int size,
                                               @RequestParam(defaultValue = "numero") String sort) {
        return fatturaService.getAllFattureSort(page, size, sort);
    }

    @GetMapping("/order-by-year")
    public Page<FatturaResponse> getAllFattureOrderByYear(@RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "10") int size) {
        return fatturaService.getAllFattureOrderByYear(page, size);
    }

    @GetMapping("/importo/range")
    public Page<FatturaResponse> getAllFattureByImportoBetween(@RequestParam double min,
                                                                @RequestParam double max,
                                                                @RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "10") int size) {
        return fatturaService.getAllFattureByImportoBetween(min, max, page, size);
    }

    @GetMapping("/stato")
    public Page<FatturaResponse> getAllFattureByStato(@RequestParam Stato stato,
                                                      @RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "10") int size) {
        return fatturaService.getAllFattureByStato(stato, page, size);
    }
}
