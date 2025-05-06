package it.epicode.EpicEnergyService.fatture;


import it.epicode.EpicEnergyService.clienti.Cliente;
import it.epicode.EpicEnergyService.clienti.ClienteRepository;
import it.epicode.EpicEnergyService.common.CommonResponse;
import it.epicode.EpicEnergyService.enums.Stato;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.List;

@Service
@Validated
public class FatturaService {

    @Autowired
    private FatturaRepository fatturaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

//    public List<FatturaResponse> getAllFatture() {
//        List<Fattura> fatture = fatturaRepository.findAll();
//        return fatture.stream()
//                .map(fattura -> new FatturaResponse(
//                        fattura.getNumero(),
//                        fattura.getData(),
//                        fattura.getImporto(),
//                        fattura.getStato(),
//                        fattura.getCliente().getId()
//                ))
//                .toList();
//    }
//
//    public List<FatturaResponse> getFattureByClienteId(Long clienteId) {
//        List<Fattura> fatture = fatturaRepository.findByClienteId(clienteId);
//        return fatture.stream()
//                .map(fattura -> new FatturaResponse(
//                        fattura.getNumero(),
//                        fattura.getData(),
//                        fattura.getImporto(),
//                        fattura.getStato(),
//                        fattura.getCliente().getId()
//                ))
//                .toList();
//    }
//
//    public List<FatturaResponse> getFattureByStato(Stato stato) {
//        List<Fattura> fatture = fatturaRepository.findByStato(stato);
//        return fatture.stream()
//                .map(fattura -> new FatturaResponse(
//                        fattura.getNumero(),
//                        fattura.getData(),
//                        fattura.getImporto(),
//                        fattura.getStato(),
//                        fattura.getCliente().getId()
//                ))
//                .toList();
//    }
//
//    public List<FatturaResponse> getFattureByData(LocalDate data) {
//        List<Fattura> fatture = fatturaRepository.findByData(data);
//        return fatture.stream()
//                .map(fattura -> new FatturaResponse(
//                        fattura.getNumero(),
//                        fattura.getData(),
//                        fattura.getImporto(),
//                        fattura.getStato(),
//                        fattura.getCliente().getId()
//                ))
//                .toList();
//    }
//
//    public List<FatturaResponse> getFattureByYear(int year) {
//        List<Fattura> fatture = fatturaRepository.findByYear(year);
//        return fatture.stream()
//                .map(fattura -> new FatturaResponse(
//                        fattura.getNumero(),
//                        fattura.getData(),
//                        fattura.getImporto(),
//                        fattura.getStato(),
//                        fattura.getCliente().getId()
//                ))
//                .toList();
//    }
//
//    public List<FatturaResponse> getFattureByImportoBetween(double min, double max) {
//        List<Fattura> fatture = fatturaRepository.findByImportoBetween(min, max);
//        return fatture.stream()
//                .map(fattura -> new FatturaResponse(
//                        fattura.getNumero(),
//                        fattura.getData(),
//                        fattura.getImporto(),
//                        fattura.getStato(),
//                        fattura.getCliente().getId()
//                ))
//                .toList();
//    }

    public CommonResponse saveFattura(FatturaRequest request) {
        Fattura fattura = new Fattura();
               fattura.setData(request.getData());
               fattura.setImporto(request.getImporto());
               fattura.setStato(request.getStato());

               Cliente cliente = clienteRepository
                       .findById(request.getClienteId())
                       .orElseThrow(() -> new EntityNotFoundException("Cliente non trovato"));

               fattura.setCliente(cliente);

        fatturaRepository.save(fattura);
        return new CommonResponse(fattura.getNumero());
    }

    public void updateFattura(Long id, FatturaRequest request){
        Fattura fattura = fatturaRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Fattura non trovata"));

        BeanUtils.copyProperties(request, fattura);
        fatturaRepository.save(fattura);
    }

    public void deleteFattura(Long id){
        Fattura fattura = fatturaRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Fattura non trovata"));

        fatturaRepository.deleteById(id);
    }

    public Page<FatturaResponse> getAllFatture(int page, int size){

        Pageable pageable = PageRequest.of(page, size);
        Page<Fattura> fatturaPage = fatturaRepository.findAll(pageable);

        return fatturaPage.map(fattura -> new FatturaResponse(
                fattura.getNumero(),
                fattura.getData(),
                fattura.getImporto(),
                fattura.getStato(),
                fattura.getCliente().getId()
        ));
    }

    public FatturaResponse findFatturaById(Long id){

        Fattura fattura = fatturaRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Fattura non trovata"));

        return new FatturaResponse(
                fattura.getNumero(),
                fattura.getData(),
                fattura.getImporto(),
                fattura.getStato(),
                fattura.getCliente().getId()
        );
    }


    public Page<FatturaResponse> getAllFattureSort(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<Fattura> fatturaPage = fatturaRepository.findAll(pageable);

        return fatturaPage.map(fattura -> new FatturaResponse(
                fattura.getNumero(),
                fattura.getData(),
                fattura.getImporto(),
                fattura.getStato(),
                fattura.getCliente().getId()
        ));
    }

    public Page<FatturaResponse> getAllFattureOrderByYear(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("data").ascending());
        Page<Fattura> fatturaPage = fatturaRepository.findAllOrderByYear(pageable);

        return fatturaPage.map(fattura -> new FatturaResponse(
                fattura.getNumero(),
                fattura.getData(),
                fattura.getImporto(),
                fattura.getStato(),
                fattura.getCliente().getId()
        ));
    }

    public Page<FatturaResponse> getAllFattureByImportoBetween(double min, double max, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Fattura> fatturaPage = fatturaRepository.findByImportoBetweenPage(min, max, pageable);

        return fatturaPage.map(fattura -> new FatturaResponse(
                fattura.getNumero(),
                fattura.getData(),
                fattura.getImporto(),
                fattura.getStato(),
                fattura.getCliente().getId()
        ));
    }

    public Page<FatturaResponse> getAllFattureByStato(Stato stato, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Fattura> fatturaPage = fatturaRepository.findByStatoPage(stato, pageable);

        return fatturaPage.map(fattura -> new FatturaResponse(
                fattura.getNumero(),
                fattura.getData(),
                fattura.getImporto(),
                fattura.getStato(),
                fattura.getCliente().getId()
        ));
    }

    public double getTotaleFatturatoByClienteId(Long clienteId){

        List<Fattura> fatture = fatturaRepository.findByClienteId(clienteId);
        return fatture.stream()
                .mapToDouble(Fattura::getImporto)
                .sum();
    }
}
