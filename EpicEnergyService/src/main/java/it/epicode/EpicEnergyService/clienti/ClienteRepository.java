package it.epicode.EpicEnergyService.clienti;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

//    @Query("SELECT c FROM Cliente c WHERE c.Comune.Provincia = :provincia")
//    List<Cliente> findAllByProvincia(@Param("provincia") String provincia);
}
