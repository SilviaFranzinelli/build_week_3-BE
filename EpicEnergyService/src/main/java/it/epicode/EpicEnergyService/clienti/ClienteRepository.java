package it.epicode.EpicEnergyService.clienti;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

//    @Query("SELECT c FROM Cliente c WHERE c.Comune.Provincia = :provincia")
//    List<Cliente> findAllByProvincia(@Param("provincia") String provincia);

    @Query("SELECT c FROM Cliente c WHERE c.fatturatoAnnuale >= :fatturatoAnnuale")
    Page<Cliente> findAllByFatturatoAnnuale(@Param("fatturatoAnnuale") double fatturatoAnnuale, Pageable pageable);

    @Query("SELECT c FROM Cliente c WHERE c.dataInserimento = :data")
    Page<Cliente> findAllByDataInserimento(@Param("data") LocalDate data, Pageable pageable);

    @Query("SELECT c FROM Cliente c WHERE c.dataUltimoContatto = :data")
    Page<Cliente> findAllByDataUltimoContatto(@Param("data") LocalDate data, Pageable pageable);

    @Query("SELECT c FROM Cliente c WHERE  c.nomeContatto LIKE %:nome%")
    Page<Cliente> findNomeContattoLike(@Param("nome") String nome, Pageable pageable);
}
