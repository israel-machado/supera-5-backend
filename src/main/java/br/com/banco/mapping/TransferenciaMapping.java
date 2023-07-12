package br.com.banco.mapping;

import br.com.banco.model.Transferencia.TransferenciaDomain;
import br.com.banco.model.Transferencia.TransferenciaFilter;
import br.com.banco.model.Transferencia.TransferenciaRequest;
import br.com.banco.model.Transferencia.TransferenciaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransferenciaMapping {

    // Mapeamento da entidade de domínio para a classe de resposta
    public static TransferenciaResponse mapToTransferenciaResponse(TransferenciaDomain transferenciaDomain) {
        return TransferenciaResponse.builder()
                .id(transferenciaDomain.getId())
                .dataTransferencia(transferenciaDomain.getDataTransferencia())
                .valor(transferenciaDomain.getValor())
                .tipo(transferenciaDomain.getTipo())
                .nomeOperadorTransacao(transferenciaDomain.getNomeOperadorTransacao())
                .conta(transferenciaDomain.getConta())
                .build();
    }

    // Mapeamento do formulário para um filtro
    public static TransferenciaFilter maptoTransferenciaFilter(TransferenciaRequest transferenciaRequest) {
        return TransferenciaFilter.builder()
                .dataInicio(transferenciaRequest.getDataInicio())
                .dataFim(transferenciaRequest.getDataFim())
                .nomeOperadorTransacao(transferenciaRequest.getNomeOperadorTransacao())
                .build();
    }
}
