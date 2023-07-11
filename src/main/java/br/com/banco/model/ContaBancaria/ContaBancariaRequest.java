package br.com.banco.model.ContaBancaria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContaBancariaRequest {

    private String numero;
    private String titular;
}
