package it.epicode.EpicEnergyService.fatture;


import it.epicode.EpicEnergyService.enums.Stato;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface FatturaRepository extends JpaRepository<Fattura, Long> {

    @Query("SELECT f FROM Fattura f WHERE f.cliente.id = :clienteId")
    List<Fattura> findByClienteId(Long clienteId);

    @Query("SELECT f FROM Fattura f WHERE f.stato = :stato")
    List<Fattura> findByStato(@Param("stato") Stato stato);

    @Query("SELECT f FROM Fattura f where f.data = :data")
    List<Fattura> findByData(LocalDate data);

    @Query("SELECT f FROM Fattura f where YEAR(f.data) = :year")
    List<Fattura> findByYear(@Param("year") int year);

    @Query("SELECT f FROM Fattura f where f.importo BETWEEN :min AND :max")
    List<Fattura> findByImportoBetween(@Param("min") double min, @Param("max") double max);

    @Query("SELECT f FROM Fattura f ORDER BY FUNCTION('YEAR', f.data) ASC")
    Page<Fattura> findAllOrderByYear(Pageable pageable);

    @Query("SELECT f FROM Fattura f where f.importo BETWEEN :min AND :max")
    Page<Fattura> findByImportoBetweenPage(@Param("min") double min, @Param("max") double max, Pageable pageable);

    @Query("SELECT f FROM Fattura f where f.stato = :stato")
    Page<Fattura> findByStatoPage(@Param("stato") Stato stato, Pageable pageable);
}