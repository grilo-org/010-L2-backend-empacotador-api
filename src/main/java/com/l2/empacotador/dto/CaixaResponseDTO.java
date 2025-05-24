package com.l2.empacotador.dto;

import java.util.List;

public class CaixaResponseDTO {

    private String caixa;
    private List<String> produtos;

    public CaixaResponseDTO() {}

    public CaixaResponseDTO(String caixa, List<String> produtos) {
        this.caixa = caixa;
        this.produtos = produtos;
    }

    public String getCaixa() {
        return caixa;
    }

    public void setCaixa(String caixa) {
        this.caixa = caixa;
    }

    public List<String> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<String> produtos) {
        this.produtos = produtos;
    }
}
