package br.com.banco.model.Transferencia;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TransferenciaFilter {

    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private String nomeOperadorTransacao;
}
