package com.yansi.cursomc.services;

import com.yansi.cursomc.domain.Cliente;
import com.yansi.cursomc.domain.ItemPedido;
import com.yansi.cursomc.domain.PagamentoComBoleto;
import com.yansi.cursomc.domain.Pedido;
import com.yansi.cursomc.enums.EstadoPagamento;
import com.yansi.cursomc.error.ObjectNotFoundException;
import com.yansi.cursomc.exceptions.AuthorizationException;
import com.yansi.cursomc.repositories.ItemPedidoRepository;
import com.yansi.cursomc.repositories.PedidoRepository;
import com.yansi.cursomc.security.UserSS;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
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

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EmailService emailService;

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
        obj.setCliente(clienteService.buscar(obj.getCliente().getId()));
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
            ip.setProduto(produtoServices.find(ip.getProduto().getId()));
            ip.setPreco(produtoServices.find(ip.getProduto().getId()).getPreco());
            ip.setPedido(obj);

        }

        itemPedidoService.save(obj.getItens());
        emailService.sendOrderConfirmationHtmlEmail(obj);
        return obj;

    }

    public Page<Pedido> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        UserSS user = UserService.authenticated();
        if (user == null) {
            throw new AuthorizationException("Acesso Negado.");
        }

        PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
        Cliente cliente = clienteService.buscar(user.getId());
        return repo.findByCliente(cliente, pageRequest);
    }

}
