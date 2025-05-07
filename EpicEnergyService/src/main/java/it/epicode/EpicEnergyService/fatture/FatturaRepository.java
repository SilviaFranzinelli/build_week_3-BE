package it.epicode.EpicEnergyService.fatture;


import org.springframework.data.jpa.repository.JpaRepository;

public interface FatturaRepository extends JpaRepository<Fattura, Long> {
}