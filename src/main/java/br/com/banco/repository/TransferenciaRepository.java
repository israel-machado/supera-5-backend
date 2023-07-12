package br.com.banco.repository;

import br.com.banco.model.Transferencia.TransferenciaDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransferenciaRepository extends JpaRepository<TransferenciaDomain, Long> {

    // Filtra as transferências pelo período de tempo e nome do operador
    List<TransferenciaDomain> findByDataTransferenciaBetweenAndNomeOperadorTransacao(LocalDateTime dataInicio, LocalDateTime dataFim, String nomeOperador);

    // Filtra as transferências pelo período de tempo
    List<TransferenciaDomain> findByDataTransferenciaBetween(LocalDateTime dataInicio, LocalDateTime dataFim);

    // Filtra as transferências pelo nome do operador
    List<TransferenciaDomain> findByNomeOperadorTransacao(String nomeOperador);
}
