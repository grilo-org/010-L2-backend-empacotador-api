package com.l2.empacotador.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.l2.empacotador.dto.CaixaResponseDTO;
import com.l2.empacotador.dto.PedidoRequestDTO;
import com.l2.empacotador.dto.PedidoResponseDTO;
import com.l2.empacotador.dto.ProdutoRequestDTO;
import com.l2.empacotador.model.CaixaResultado;
import com.l2.empacotador.model.Pedido;
import com.l2.empacotador.model.Produto;
import com.l2.empacotador.service.EmpacotadorService;

@RestController
@RequestMapping("/api")
public class EmpacotadorController {

    private final EmpacotadorService service = new EmpacotadorService();

    @PostMapping("/pedidos")
    public List<PedidoResponseDTO> empacotar(@RequestBody List<PedidoRequestDTO> pedidosDTO) {
        List<Pedido> pedidosModel = new ArrayList<>();

        for (PedidoRequestDTO pedidoDTO : pedidosDTO) {
            List<Produto> produtosModel = new ArrayList<>();

            for (ProdutoRequestDTO p : pedidoDTO.getProdutos()) {
                Produto prod = new Produto(p.getId(), p.getAltura(), p.getLargura(), p.getComprimento());
                produtosModel.add(prod);
            }

            Pedido pedido = new Pedido(pedidoDTO.getId(), produtosModel);
            pedidosModel.add(pedido);
        }

        Map<String, List<CaixaResultado>> resultado = service.empacotar(pedidosModel);

        List<PedidoResponseDTO> respostas = new ArrayList<>();

        for (PedidoRequestDTO pedidoDTO : pedidosDTO) {
            List<CaixaResultado> caixas = resultado.get(pedidoDTO.getId());
            List<CaixaResponseDTO> caixasDTO = new ArrayList<>();

            if (caixas != null) {
                for (CaixaResultado c : caixas) {
                    caixasDTO.add(new CaixaResponseDTO(c.getCaixa(), c.getProdutos()));
                }
            }

            respostas.add(new PedidoResponseDTO(pedidoDTO.getId(), caixasDTO));
        }

        return respostas;
    }
}
