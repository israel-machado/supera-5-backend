package br.com.banco;

import br.com.banco.model.Transferencia.TransferenciaDomain;
import br.com.banco.model.Transferencia.TransferenciaRequest;
import br.com.banco.model.Transferencia.TransferenciaResponse;
import br.com.banco.repository.TransferenciaRepository;
import br.com.banco.service.ExtratoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ExtratoServiceTest {

    @Mock
    private TransferenciaRepository transferenciaRepository;

    @InjectMocks
    private ExtratoService extratoService;

    @Test
    public void testObterExtratoSemFiltros() {
        // Mock dos dados de transferências retornados pelo repositório
        List<TransferenciaDomain> transferenciasMock = Arrays.asList(
                new TransferenciaDomain(1L, LocalDateTime.now(), BigDecimal.valueOf(100), "DEPOSITO", null, null),
                new TransferenciaDomain(2L, LocalDateTime.now(), BigDecimal.valueOf(50), "SAQUE", null, null)
        );

        // Configuração do comportamento do repositório mockado
        Mockito.when(transferenciaRepository.findAll()).thenReturn(transferenciasMock);

        // Chama o método de obter extrato sem filtros
        List<TransferenciaResponse> extrato = extratoService.obterExtrato(null);

        // Verifica se o extrato retornou as transferências esperadas
        Assert.assertEquals(2, extrato.size());
        Assert.assertEquals(1L, extrato.get(0).getId().longValue());
        Assert.assertEquals(2L, extrato.get(1).getId().longValue());
    }

    @Test
    public void testObterExtratoComFiltros() {
        // Mock dos dados de transferências retornados pelo repositório
        List<TransferenciaDomain> transferenciasMock = Arrays.asList(
                new TransferenciaDomain(1L, LocalDateTime.now(), BigDecimal.valueOf(100), "DEPOSITO", null, null),
                new TransferenciaDomain(2L, LocalDateTime.now(), BigDecimal.valueOf(50), "SAQUE", null, null)
        );

        // Configuração do comportamento do repositório mockado
        Mockito.when(transferenciaRepository.findByDataTransferenciaBetweenAndNomeOperadorTransacao(Mockito.any(LocalDateTime.class), Mockito.any(LocalDateTime.class), Mockito.anyString()))
                .thenReturn(transferenciasMock);

        // Chama o método de obter extrato com filtros
        TransferenciaRequest transferenciaRequest = new TransferenciaRequest();
        transferenciaRequest.setDataInicio(LocalDateTime.now().minusDays(1));
        transferenciaRequest.setDataFim(LocalDateTime.now());
        transferenciaRequest.setNomeOperadorTransacao("João");

        List<TransferenciaResponse> extrato = extratoService.obterExtrato(transferenciaRequest);

        // Verifica se o extrato retornou as transferências esperadas
        Assert.assertEquals(2, extrato.size());
        Assert.assertEquals(1L, extrato.get(0).getId().longValue());
        Assert.assertEquals(2L, extrato.get(1).getId().longValue());
    }

    @Test
    public void testObterExtratoPorPeriodo() {
        // Mock dos dados de transferências retornados pelo repositório
        List<TransferenciaDomain> transferenciasMock = Arrays.asList(
                new TransferenciaDomain(1L, LocalDateTime.now(), BigDecimal.valueOf(100), "DEPOSITO", null, null),
                new TransferenciaDomain(2L, LocalDateTime.now(), BigDecimal.valueOf(50), "SAQUE", null, null)
        );

        // Configuração do comportamento do repositório mockado
        LocalDateTime dataInicio = LocalDateTime.of(2022, 1, 1, 0, 0);
        LocalDateTime dataFim = LocalDateTime.of(2022, 12, 31, 23, 59);
        Mockito.when(transferenciaRepository.findByDataTransferenciaBetween(dataInicio, dataFim))
                .thenReturn(transferenciasMock);

        // Chama o método de obter extrato com filtro de período
        TransferenciaRequest transferenciaRequest = new TransferenciaRequest();
        transferenciaRequest.setDataInicio(dataInicio);
        transferenciaRequest.setDataFim(dataFim);

        List<TransferenciaResponse> extrato = extratoService.obterExtrato(transferenciaRequest);

        // Verifica se o extrato retornou as transferências esperadas
        Assert.assertEquals(2, extrato.size());
        Assert.assertEquals(1L, extrato.get(0).getId().longValue());
        Assert.assertEquals(2L, extrato.get(1).getId().longValue());
    }

    @Test
    public void testObterExtratoPorNomeOperador() {
        // Mock dos dados de transferências retornados pelo repositório
        List<TransferenciaDomain> transferenciasMock = Arrays.asList(
                new TransferenciaDomain(1L, LocalDateTime.now(), BigDecimal.valueOf(100), "DEPOSITO", "João", null),
                new TransferenciaDomain(2L, LocalDateTime.now(), BigDecimal.valueOf(50), "SAQUE", "Maria", null)
        );

        // Configuração do comportamento do repositório mockado
        String nomeOperador = "João";
        Mockito.when(transferenciaRepository.findByNomeOperadorTransacao(nomeOperador))
                .thenReturn(transferenciasMock);

        // Chama o método de obter extrato com filtro por nome do operador
        TransferenciaRequest transferenciaRequest = new TransferenciaRequest();
        transferenciaRequest.setNomeOperadorTransacao(nomeOperador);

        List<TransferenciaResponse> extrato = extratoService.obterExtrato(transferenciaRequest);

        // Verifica se o extrato retornou as transferências esperadas
        Assert.assertEquals(1, extrato.size());
        Assert.assertEquals(1L, extrato.get(0).getId().longValue());
    }

    @Test
    public void testObterExtratoSemFiltrosVazio() {
        // Configuração do comportamento do repositório mockado
        Mockito.when(transferenciaRepository.findAll()).thenReturn(Collections.emptyList());

        // Chama o método de obter extrato sem filtros
        List<TransferenciaResponse> extrato = extratoService.obterExtrato(null);

        // Verifica se o extrato está vazio
        Assert.assertTrue(extrato.isEmpty());
    }

}
