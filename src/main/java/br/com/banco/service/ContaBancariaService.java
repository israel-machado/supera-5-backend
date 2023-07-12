package br.com.banco.service;

import br.com.banco.model.ContaBancaria.ContaBancariaDomain;
import br.com.banco.repository.ContaBancariaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContaBancariaService {

    private final ContaBancariaRepository contaBancariaRepository;

    public List<ContaBancariaDomain> buscarContasBancarias() {
        return contaBancariaRepository.findAll();
    }
}
