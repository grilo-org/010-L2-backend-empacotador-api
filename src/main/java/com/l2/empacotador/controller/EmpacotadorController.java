package com.l2.empacotador.controller;

import com.l2.empacotador.model.PedidoRequest;
import com.l2.empacotador.service.EmpacotadorService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedidos")
public class EmpacotadorController {

    private final EmpacotadorService service = new EmpacotadorService();

    @PostMapping
    public String empacotar(@RequestBody PedidoRequest request) {
        return service.empacotar(request.pedidos);
    }
}