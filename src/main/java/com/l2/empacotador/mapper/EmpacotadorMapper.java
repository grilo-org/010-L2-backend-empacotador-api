package com.l2.empacotador.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.l2.empacotador.dto.CaixaResponseDTO;
import com.l2.empacotador.dto.PedidoRequestDTO;
import com.l2.empacotador.dto.PedidoResponseDTO;
import com.l2.empacotador.dto.ProdutoResponseDTO;
import com.l2.empacotador.model.CaixaResultado;
import com.l2.empacotador.model.Pedido;
import com.l2.empacotador.model.Produto;

public class EmpacotadorMapper {

    // Mapeia PedidoRequestDTO para Pedido (modelo)
    public static Pedido toModel(PedidoRequestDTO dto) {
        List<Produto> produtos = dto.getProdutos().stream()
                .map(p -> new Produto(p.getId(), p.getAltura(), p.getLargura(), p.getComprimento()))
                .collect(Collectors.toList());

        return new Pedido(dto.getId(), produtos);
    }

    // Mapeia Pedido (modelo) para PedidoResponseDTO
    public static PedidoResponseDTO toDTO(Pedido pedido, List<CaixaResultado> caixas) {
        List<CaixaResponseDTO> caixasDTO = caixas.stream()
                .map(c -> new CaixaResponseDTO(c.getCaixa(), c.getProdutos()))
                .collect(Collectors.toList());

        return new PedidoResponseDTO(pedido.getId(), caixasDTO);
    }

    // Mapeia Produto (modelo) para ProdutoResponseDTO
    public static ProdutoResponseDTO toDTO(Produto produto) {
        return new ProdutoResponseDTO(produto.getId(), produto.getAltura(), produto.getLargura(), produto.getComprimento());
    }
}
