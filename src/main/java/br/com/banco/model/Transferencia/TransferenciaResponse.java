package br.com.banco.model.Transferencia;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransferenciaResponse {

    private Long id;
    private String numeroContaOrigem;
    private String numeroContaDestino;
    private String operadorTransacao;
    private BigDecimal valor;
    private LocalDateTime data;
}
