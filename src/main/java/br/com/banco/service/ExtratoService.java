package br.com.banco.service;

import br.com.banco.mapping.TransferenciaMapping;
import br.com.banco.model.Transferencia.TransferenciaDomain;
import br.com.banco.model.Transferencia.TransferenciaFilter;
import br.com.banco.model.Transferencia.TransferenciaRequest;
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

    public List<TransferenciaResponse> obterExtrato(TransferenciaRequest transferenciaRequest) {

        // Mapeia o objeto TransferenciaRequest para TransferenciaFilter
        TransferenciaFilter filter = TransferenciaMapping.maptoTransferenciaFilter(transferenciaRequest);

        // Inicia uma lista de transferencias do tipo domínio
        List<TransferenciaDomain> transferencias;

        // Realiza a busca de acordo com os filtros fornecidos
        if (filter.getDataInicio() != null && filter.getDataFim() != null && filter.getNomeOperadorTransacao() != null) {
            transferencias = transferenciaRepository.findByDataTransferenciaBetweenAndNomeOperadorTransacao(filter.getDataInicio(), filter.getDataFim(), filter.getNomeOperadorTransacao());
        } else if (filter.getDataInicio() != null && filter.getDataFim() != null) {
            transferencias = transferenciaRepository.findByDataTransferenciaBetween(filter.getDataInicio(), filter.getDataFim());
        } else if (filter.getNomeOperadorTransacao() != null) {
            transferencias = transferenciaRepository.findByNomeOperadorTransacao(filter.getNomeOperadorTransacao());
        } else {
            transferencias = transferenciaRepository.findAll();
        }

        // Mapeia a lista de TransferenciaDomain para TransferenciaResponse
        return transferencias.stream()
                .map(TransferenciaMapping::mapToTransferenciaResponse)
                .collect(Collectors.toList());
    }

    public TransferenciaRequest getTransferenciaRequest(LocalDateTime dataInicio, LocalDateTime dataFim, String nomeOperadorTransacao) {

        // Cria e retorna um objeto TransferenciaRequest com base nos parâmetros fornecidos
        return TransferenciaRequest.builder()
                .dataInicio(dataInicio)
                .dataFim(dataFim)
                .nomeOperadorTransacao(nomeOperadorTransacao)
                .build();
    }
}
