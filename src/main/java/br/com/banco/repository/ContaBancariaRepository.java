package br.com.banco.repository;

import br.com.banco.model.ContaBancaria.ContaBancariaDomain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaBancariaRepository extends JpaRepository<ContaBancariaDomain, Long> {
}
