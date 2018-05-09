/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yansi.cursomc.services;

import com.yansi.cursomc.domain.ItemPedido;
import com.yansi.cursomc.domain.PagamentoComBoleto;
import com.yansi.cursomc.domain.Pedido;
import com.yansi.cursomc.enums.EstadoPagamento;
import com.yansi.cursomc.error.ObjectNotFoundException;
import com.yansi.cursomc.repositories.ItemPedidoRepository;
import com.yansi.cursomc.repositories.PedidoRepository;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repo;

    @Autowired
    private BoletoService boletoService;

    @Autowired
    private PagamentoService pagamentoService;

    @Autowired
    private ProdutoServices produtoServices;

    @Autowired
    private ItemPedidoRepository itemPedidoService;

    public void saveList(List<Pedido> asList) {
        repo.save(asList);
    }

    public Pedido bucar(Integer id) {
        Pedido obj = repo.findOne(id);

        if (obj == null) {
            throw new ObjectNotFoundException("Objeto não encontrado! ID: " + id + " Tipo: " + Pedido.class.getName());
        }

        return obj;
    }

    public Pedido insert(Pedido obj) {
        obj.setId(null);
        obj.setInstante(new Date());
        obj.getPagamento().setEstadoPagamento(EstadoPagamento.PENDENTE);
        obj.getPagamento().setPedido(obj);

        //Calcula a data para pagemento.
        if (obj.getPagamento() instanceof PagamentoComBoleto) {
            PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
            boletoService.preenchePagamentoComBoleto(pagto, obj.getInstante());
        }

        obj = repo.save(obj);
        pagamentoService.saveList(Arrays.asList(obj.getPagamento()));

        //Pega a lista de Pedido.
        for (ItemPedido ip : obj.getItens()) {
            ip.setDesconto(0.0);
            ip.setPreco(produtoServices.find(ip.getProduto().getId()).getPreco());
            ip.setPedido(obj);

        }

        itemPedidoService.save(obj.getItens());
        return obj;

    }

}
