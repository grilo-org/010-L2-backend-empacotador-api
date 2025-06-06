package com.l2.empacotador.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.l2.empacotador.model.CaixaResultado;
import com.l2.empacotador.model.Pedido;
import com.l2.empacotador.model.Produto;

public class EmpacotadorService {

    // Define as caixas disponíveis com dimensões (altura, largura, comprimento) e nome
    private static final List<Caixa> caixas = List.of(
            new Caixa("Caixa 1", 30, 40, 80),
            new Caixa("Caixa 2", 80, 50, 40),
            new Caixa("Caixa 3", 50, 80, 60)
    );

    // Método principal que recebe os pedidos e devolve um resumo do empacotamento
    public Map<String, List<CaixaResultado>> empacotar(List<Pedido> pedidos) {
        Map<String, List<CaixaResultado>> resultado = new HashMap<>();

        for (Pedido pedido : pedidos) {
            List<CaixaResultado> caixasDoPedido = new ArrayList<>();

            // Lista de caixas abertas para esse pedido
            List<CaixaAberta> caixasAbertas = new ArrayList<>();

            for (Produto produto : pedido.produtos) {
                // Ignorar produtos com volume zero ou negativo
                if (produto.getVolume() <= 0) {
                    continue;
                }

                boolean encaixou = false;

                // Tenta colocar o produto em alguma caixa aberta
                for (CaixaAberta caixaAberta : caixasAbertas) {
                    if (caixaAberta.cabe(produto)) {
                        caixaAberta.produtos.add(produto);
                        encaixou = true;
                        break;
                    }
                }

                // Se não coube em nenhuma caixa aberta, abre uma nova
                if (!encaixou) {
                    // Acha a menor caixa que cabe o produto
                    Optional<Caixa> caixaAdequada = caixas.stream()
                            .filter(c -> c.cabe(produto))
                            .findFirst();

                    // Se não encontrar caixa adequada, ignora o produto (não lança exceção)
                    if (caixaAdequada.isEmpty()) {
                        continue;
                    }

                    Caixa novaCaixa = caixaAdequada.get();
                    CaixaAberta caixaAberta = new CaixaAberta(novaCaixa);
                    caixaAberta.produtos.add(produto);
                    caixasAbertas.add(caixaAberta);
                }
            }

            // Transformar as caixas abertas no formato de saída usando a classe externa
            for (CaixaAberta aberta : caixasAbertas) {
                List<String> idsProdutos = new ArrayList<>();
                for (Produto p : aberta.produtos) {
                    idsProdutos.add(p.getId());
                }
                caixasDoPedido.add(new CaixaResultado(aberta.caixa.nome, idsProdutos));
            }

            resultado.put(pedido.getId(), caixasDoPedido);
        }

        return resultado;
    }

    // Classe auxiliar para representar caixas disponíveis
    private static class Caixa {
        String nome;
        double altura, largura, comprimento;
        double volume;

        Caixa(String nome, double altura, double largura, double comprimento) {
            this.nome = nome;
            this.altura = altura;
            this.largura = largura;
            this.comprimento = comprimento;
            this.volume = altura * largura * comprimento;
        }

        // Verifica se o produto cabe nesta caixa (considerando volume e dimensões)
        boolean cabe(Produto produto) {
            // Verifica todas as possíveis orientações do produto para caber na caixa
            return (produto.getAltura() <= altura && produto.getLargura() <= largura && produto.getComprimento() <= comprimento)
                    || (produto.getAltura() <= altura && produto.getComprimento() <= largura && produto.getLargura() <= comprimento)
                    || (produto.getLargura() <= altura && produto.getAltura() <= largura && produto.getComprimento() <= comprimento)
                    || (produto.getLargura() <= altura && produto.getComprimento() <= largura && produto.getAltura() <= comprimento)
                    || (produto.getComprimento() <= altura && produto.getAltura() <= largura && produto.getLargura() <= comprimento)
                    || (produto.getComprimento() <= altura && produto.getLargura() <= largura && produto.getAltura() <= comprimento);
        }
    }

    // Caixa aberta para colocar produtos
    private static class CaixaAberta {
        Caixa caixa;
        List<Produto> produtos = new ArrayList<>();

        CaixaAberta(Caixa caixa) {
            this.caixa = caixa;
        }

        // Verifica se ainda cabe o produto nesta caixa (baseado no volume somado dos produtos)
        boolean cabe(Produto produto) {
            double volumeUsado = produtos.stream().mapToDouble(Produto::getVolume).sum();
            return volumeUsado + produto.getVolume() <= caixa.volume && caixa.cabe(produto);
        }
    }
}
