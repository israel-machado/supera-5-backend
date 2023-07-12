package br.com.banco.repository;

import br.com.banco.model.Transferencia.TransferenciaDomain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferenciaRepository extends JpaRepository<TransferenciaDomain, Long> {
}
