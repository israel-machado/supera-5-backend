package br.com.banco.model.ContaBancaria;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ContaBancariaResponse {

    private Long id;
    private String nomeResponsavel;
}
