package com.l2.empacotador.model;

public class Produto {
    public String id;
    public double altura;
    public double largura;
    public double comprimento;

    public double getVolume() {
        return altura * largura * comprimento;
    }
}