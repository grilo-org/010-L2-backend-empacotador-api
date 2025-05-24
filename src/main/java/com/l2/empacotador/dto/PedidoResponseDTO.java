package com.l2.empacotador.dto;

import java.util.List;

public class PedidoResponseDTO {

    private String id;
    private List<CaixaResponseDTO> caixas;

    public PedidoResponseDTO() {}

    public PedidoResponseDTO(String id, List<CaixaResponseDTO> caixas) {
        this.id = id;
        this.caixas = caixas;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<CaixaResponseDTO> getCaixas() {
        return caixas;
    }

    public void setCaixas(List<CaixaResponseDTO> caixas) {
        this.caixas = caixas;
    }
}
