package br.com.banco.mapping;

import br.com.banco.model.Transferencia.TransferenciaDomain;
import br.com.banco.model.Transferencia.TransferenciaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransferenciaMapping {

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
}
