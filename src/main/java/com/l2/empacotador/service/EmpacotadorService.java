package com.l2.empacotador.service;

import java.util.List;

import com.l2.empacotador.model.Pedido;

public class EmpacotadorService {

    public String empacotar(List<Pedido> pedidos) {
        return "Lógica de empacotamento vai aqui! " + pedidos.size() + " pedidos recebidos.";
    }
}