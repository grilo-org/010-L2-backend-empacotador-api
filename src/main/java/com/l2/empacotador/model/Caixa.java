package com.l2.empacotador.model;

import java.util.ArrayList;
import java.util.List;

public class Caixa {

    private static final double LIMITE_VOLUME = 100000.0;
    public List<Produto> produtos = new ArrayList<>();

    public boolean cabe(Produto produto) {
        return produto.getVolume() + getVolumeAtual() <= LIMITE_VOLUME;
    }

    public void adicionarProduto(Produto produto) {
        if (cabe(produto)) {
            produtos.add(produto);
        }
    }

    public double getVolumeAtual() {
        return produtos.stream().mapToDouble(Produto::getVolume).sum();
    }
}
