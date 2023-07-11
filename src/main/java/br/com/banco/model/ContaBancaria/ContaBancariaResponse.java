package br.com.banco.model.ContaBancaria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContaBancariaResponse {

    private Long id;
    private String numero;
    private String titular;
}
