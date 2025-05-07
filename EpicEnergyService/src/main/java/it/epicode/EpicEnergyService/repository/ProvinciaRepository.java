package it.epicode.EpicEnergyService.repository;

import it.epicode.EpicEnergyService.model.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProvinciaRepository extends JpaRepository<Provincia, Long> {
    Optional<Provincia> findByNomeAndSigla(String nome, String sigla);
}