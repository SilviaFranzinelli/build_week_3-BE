package it.epicode.EpicEnergyService.repository;

import it.epicode.EpicEnergyService.model.Comune;
import it.epicode.EpicEnergyService.model.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ComuneRepository extends JpaRepository<Comune, Long> {
    Optional<Comune> findByNomeAndProvincia(String nome, Provincia provincia);
}