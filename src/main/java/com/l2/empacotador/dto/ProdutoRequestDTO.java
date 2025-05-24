package com.l2.empacotador.dto;

public class ProdutoRequestDTO {

    private String id;
    private double altura;
    private double largura;
    private double comprimento;

    public ProdutoRequestDTO() {}

    public ProdutoRequestDTO(String id, double altura, double largura, double comprimento) {
        this.id = id;
        this.altura = altura;
        this.largura = largura;
        this.comprimento = comprimento;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getLargura() {
        return largura;
    }

    public void setLargura(double largura) {
        this.largura = largura;
    }

    public double getComprimento() {
        return comprimento;
    }

    public void setComprimento(double comprimento) {
        this.comprimento = comprimento;
    }
}
