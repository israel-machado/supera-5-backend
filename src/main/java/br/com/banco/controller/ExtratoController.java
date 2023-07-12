package br.com.banco.controller;

import br.com.banco.model.Transferencia.TransferenciaRequest;
import br.com.banco.model.Transferencia.TransferenciaResponse;
import br.com.banco.service.ExtratoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/extrato")
public class ExtratoController {

    private final ExtratoService extratoService;

    @GetMapping
    public ResponseEntity<List<TransferenciaResponse>> obterExtrato(@RequestParam(value = "dataInicio", required = false) LocalDateTime dataInicio,
                                                                    @RequestParam(value = "dataFim", required = false) LocalDateTime dataFim,
                                                                    @RequestParam(value = "nomeOperador", required = false) String nomeOperador) {

        // Passa os parâmetros recebidos para o serviço obter o objeto TransferenciaRequest adequado
        TransferenciaRequest transferenciaRequest = extratoService.getTransferenciaRequest(dataInicio, dataFim, nomeOperador);

        // Chama o serviço para obter o extrato com base no TransferenciaRequest
        List<TransferenciaResponse> extrato = extratoService.obterExtrato(transferenciaRequest);

        // Retorna a lista de TransferenciaResponse na resposta HTTP 200
        return ResponseEntity.ok(extrato);
    }
}
