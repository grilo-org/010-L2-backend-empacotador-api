package com.l2.empacotador.model;

import java.util.List;

public class Pedido {

    public String id;
    public List<Produto> produtos;

    public Pedido(String id, List<Produto> produtos) {
        this.id = id;
        this.produtos = produtos;
    }

    public String getId() {
        return id;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }
}
