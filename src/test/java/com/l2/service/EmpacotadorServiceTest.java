package com.l2.empacotador.service;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.l2.empacotador.model.CaixaResultado;
import com.l2.empacotador.model.Pedido;
import com.l2.empacotador.model.Produto;

public class EmpacotadorServiceTest {

    private final EmpacotadorService service = new EmpacotadorService();

    @Test
    void deveEmpacotarProdutoSimples() {
        Produto produto = new Produto("produto1", 20, 30, 40); // volume: 24000
        Pedido pedido = new Pedido("pedido001", List.of(produto));

        Map<String, List<CaixaResultado>> resultado = service.empacotar(List.of(pedido));

        assertNotNull(resultado);
        assertTrue(resultado.containsKey("pedido001"));
        List<CaixaResultado> caixas = resultado.get("pedido001");
        assertNotNull(caixas);
        assertEquals(1, caixas.size());
        // verificar se produto está na lista da caixa pelo id
        assertTrue(caixas.get(0).getProdutos().contains(produto.getId()));
    }

    @Test
    void deveIgnorarProdutoMaiorQueQualquerCaixa() {
        Produto gigante = new Produto("monstro", 999, 999, 999); // absurdo
        Pedido pedido = new Pedido("pedidoFail", List.of(gigante));

        Map<String, List<CaixaResultado>> resultado = service.empacotar(List.of(pedido));

        assertNotNull(resultado);
        assertTrue(resultado.containsKey("pedidoFail"));
        assertTrue(resultado.get("pedidoFail").isEmpty());
    }

    @Test
    void deveIgnorarProdutoComVolumeZero() {
        Produto zerado = new Produto("vazio", 0, 20, 30); // volume: 0
        Pedido pedido = new Pedido("pedidoZero", List.of(zerado));

        Map<String, List<CaixaResultado>> resultado = service.empacotar(List.of(pedido));

        assertNotNull(resultado);
        assertTrue(resultado.containsKey("pedidoZero"));
        assertTrue(resultado.get("pedidoZero").isEmpty());
    }

    @Test
    void deveLidarComPedidoSemProdutos() {
        Pedido vazio = new Pedido("pedidoVazio", List.of());

        Map<String, List<CaixaResultado>> resultado = service.empacotar(List.of(vazio));

        assertNotNull(resultado);
        assertTrue(resultado.containsKey("pedidoVazio"));
        assertTrue(resultado.get("pedidoVazio").isEmpty());
    }

    @Test
    void deveEmpacotarMultiplosPedidos() {
        Produto p1 = new Produto("p1", 10, 10, 10); // 1000
        Produto p2 = new Produto("p2", 20, 20, 20); // 8000

        Pedido pedido1 = new Pedido("A", List.of(p1));
        Pedido pedido2 = new Pedido("B", List.of(p2));

        Map<String, List<CaixaResultado>> resultado = service.empacotar(List.of(pedido1, pedido2));

        assertEquals(2, resultado.size());
        assertTrue(resultado.containsKey("A"));
        assertTrue(resultado.containsKey("B"));

        assertEquals(1, resultado.get("A").size());
        assertEquals(1, resultado.get("B").size());

        assertTrue(resultado.get("A").get(0).getProdutos().contains(p1.getId()));
        assertTrue(resultado.get("B").get(0).getProdutos().contains(p2.getId()));
    }
}
