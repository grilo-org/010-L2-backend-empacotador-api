package com.l2.empacotador.model;

import java.util.List;

public class CaixaResultado {

    private String caixa;
    private List<String> produtos;

    public CaixaResultado(String caixa, List<String> produtos) {
        this.caixa = caixa;
        this.produtos = produtos;
    }

    public String getCaixa() {
        return caixa;
    }

    public List<String> getProdutos() {
        return produtos;
    }
}
