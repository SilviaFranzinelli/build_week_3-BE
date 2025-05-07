package it.epicode.EpicEnergyService.model;

import it.epicode.EpicEnergyService.clienti.Cliente;
import it.epicode.EpicEnergyService.clienti.ClienteRepository;
import it.epicode.EpicEnergyService.exceptions.ResourceNotFoundException;
import it.epicode.EpicEnergyService.repository.IndirizzoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor
public class IndirizzoService {
    @Autowired
    private final IndirizzoRepository indirizzoRepository;

    public Indirizzo save(Indirizzo indirizzo) {
        return indirizzoRepository.save(indirizzo);
    }
}
