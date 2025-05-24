package com.l2.empacotador.model;

public class Produto {

    public String id;
    public double altura;
    public double largura;
    public double comprimento;

    public Produto(String id, double altura, double largura, double comprimento) {
        this.id = id;
        this.altura = altura;
        this.largura = largura;
        this.comprimento = comprimento;
    }

    public String getId() {
        return id;
    }

    public double getAltura() {
        return altura;
    }

    public double getLargura() {
        return largura;
    }

    public double getComprimento() {
        return comprimento;
    }

    public double getVolume() {
        return altura * largura * comprimento;
    }
}
