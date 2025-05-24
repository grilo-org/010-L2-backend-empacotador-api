package com.l2.empacotador.dto;

import java.util.List;

public class PedidoRequestDTO {

    private String id;
    private List<ProdutoRequestDTO> produtos;

    public PedidoRequestDTO() {}

    public PedidoRequestDTO(String id, List<ProdutoRequestDTO> produtos) {
        this.id = id;
        this.produtos = produtos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ProdutoRequestDTO> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoRequestDTO> produtos) {
        this.produtos = produtos;
    }
}
