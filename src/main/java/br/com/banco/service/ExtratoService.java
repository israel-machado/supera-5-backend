package br.com.banco.service;

import br.com.banco.mapping.TransferenciaMapping;
import br.com.banco.model.Transferencia.TransferenciaDomain;
import br.com.banco.model.Transferencia.TransferenciaResponse;
import br.com.banco.repository.TransferenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExtratoService {

    private final TransferenciaRepository transferenciaRepository;

    public List<TransferenciaResponse> obterExtrato(LocalDateTime dataInicio, LocalDateTime dataFim, String nomeOperador) {
        List<TransferenciaDomain> transferencias;

        if (dataInicio != null && dataFim != null && nomeOperador != null) {
            // Filtra as transferências pelo período de tempo e nome do operador
            transferencias = transferenciaRepository.findByDataTransferenciaBetweenAndNomeOperadorTransacao(dataInicio, dataFim, nomeOperador);
        } else if (dataInicio != null && dataFim != null) {
            // Filtra as transferências pelo período de tempo
            transferencias = transferenciaRepository.findByDataTransferenciaBetween(dataInicio, dataFim);
        } else if (nomeOperador != null) {
            // Filtra as transferências pelo nome do operador
            transferencias = transferenciaRepository.findByNomeOperadorTransacao(nomeOperador);
        } else {
            // Caso nenhum filtro seja aplicado, retorna todas as transferências
            transferencias = transferenciaRepository.findAll();
        }

        return transferencias.stream()
                .map(TransferenciaMapping::mapToTransferenciaResponse)
                .collect(Collectors.toList());
    }
}
