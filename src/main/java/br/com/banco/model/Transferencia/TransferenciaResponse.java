package br.com.banco.model.Transferencia;

import br.com.banco.model.ContaBancaria.ContaBancariaDomain;
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
    private LocalDateTime dataTransferencia;
    private BigDecimal valor;
    private String tipo;
    private String nomeOperadorTransacao;
    private ContaBancariaDomain conta;
}
