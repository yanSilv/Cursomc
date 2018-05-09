package com.yansi.cursomc.services.test;

import com.yansi.cursomc.domain.Categoria;
import com.yansi.cursomc.domain.Cidade;
import com.yansi.cursomc.domain.Cliente;
import com.yansi.cursomc.domain.Endereco;
import com.yansi.cursomc.domain.Estado;
import com.yansi.cursomc.domain.ItemPedido;
import com.yansi.cursomc.domain.PagamentoComBoleto;
import com.yansi.cursomc.domain.PagamentoComCartao;
import com.yansi.cursomc.domain.Pedido;
import com.yansi.cursomc.domain.Produto;
import com.yansi.cursomc.enums.EstadoPagamento;
import com.yansi.cursomc.enums.TipoCliente;
import com.yansi.cursomc.services.CategoriaService;
import com.yansi.cursomc.services.CidadeService;
import com.yansi.cursomc.services.ClienteService;
import com.yansi.cursomc.services.EnderecoService;
import com.yansi.cursomc.services.EstadoService;
import com.yansi.cursomc.services.ItemPedidoService;
import com.yansi.cursomc.services.PagamentoService;
import com.yansi.cursomc.services.PedidoService;
import com.yansi.cursomc.services.ProdutoServices;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DBServices {

    @Autowired
    CategoriaService serviceCategoria;

    @Autowired
    ProdutoServices serviceProduto;

    @Autowired
    EstadoService serviceEstado;

    @Autowired
    CidadeService serviceCidade;

    @Autowired
    ClienteService serviceCliente;

    @Autowired
    EnderecoService serviceEndereco;

    @Autowired
    PedidoService servicePedido;

    @Autowired
    PagamentoService servicePagamento;

    @Autowired
    ItemPedidoService serviceItemPedido;

    public void instantiateTestDatabase() throws ParseException {
        //Inicializa Categoria
        Categoria cat1 = new Categoria(null, "Informatica");
        Categoria cat2 = new Categoria(null, "Escritorio");
        Categoria cat3 = new Categoria(null, "Colégio");
        Categoria cat4 = new Categoria(null, "Faculdade");
        Categoria cat5 = new Categoria(null, "Clube");
        Categoria cat6 = new Categoria(null, "Academia");
        Categoria cat7 = new Categoria(null, "Ginastica");
        Categoria cat8 = new Categoria(null, "Natação");
        Categoria cat9 = new Categoria(null, "Biblioteca");
        Categoria cat10 = new Categoria(null, "Mercado");
        Categoria cat11 = new Categoria(null, "Cinema");
        Categoria cat12 = new Categoria(null, "Shopping");
        Categoria cat13 = new Categoria(null, "Cosmedicos");
        Categoria cat14 = new Categoria(null, "Farmacia");
        Categoria cat15 = new Categoria(null, "Feira");
        Categoria cat16 = new Categoria(null, "Esporte");
        Categoria cat17 = new Categoria(null, "Biologico");
        Categoria cat18 = new Categoria(null, "Computador");
        Categoria cat19 = new Categoria(null, "Programação");
        Categoria cat20 = new Categoria(null, "Aviação");

        //Inicializa Produto
        Produto pro1 = new Produto(null, "Computador", 2000.00);
        Produto pro2 = new Produto(null, "Impressora", 800.00);
        Produto pro3 = new Produto(null, "Mouse", 80.00);
        Produto p4 = new Produto(null, "Mesa de escritório", 300.00);
        Produto p5 = new Produto(null, "Toalha", 50.00);
        Produto p6 = new Produto(null, "Colcha", 200.00);
        Produto p7 = new Produto(null, "TV true color", 1200.00);
        Produto p8 = new Produto(null, "Roçadeira", 800.00);
        Produto p9 = new Produto(null, "Abajour", 100.00);
        Produto p10 = new Produto(null, "Pendente", 180.00);
        Produto p11 = new Produto(null, "Shampoo", 90.00);

        //Inicializa Estado
        Estado est1 = new Estado(null, "Minhas Gerais");
        Estado est2 = new Estado(null, "São Paulo");

        //Inicializa Cidade
        Cidade c1 = new Cidade(null, "Uberlandia", est1);
        Cidade c2 = new Cidade(null, "São Paulo", est2);
        Cidade c3 = new Cidade(null, "Campinas", est2);

        cat1.getProdutos().addAll(Arrays.asList(pro1, pro2, pro3));
        cat2.getProdutos().addAll(Arrays.asList(pro2, p4));

        cat3.getProdutos().addAll(Arrays.asList(p5, p6));
        cat4.getProdutos().addAll(Arrays.asList(pro1, pro2, pro3, p7));
        cat5.getProdutos().addAll(Arrays.asList(p8));
        cat6.getProdutos().addAll(Arrays.asList(p9, p10));
        cat7.getProdutos().addAll(Arrays.asList(p11));

        pro1.getCategorias().addAll(Arrays.asList(cat1, cat4));
        pro2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
        pro3.getCategorias().addAll(Arrays.asList(cat1, cat4));

        p4.getCategorias().addAll(Arrays.asList(cat2));
        p5.getCategorias().addAll(Arrays.asList(cat3));
        p6.getCategorias().addAll(Arrays.asList(cat3));
        p7.getCategorias().addAll(Arrays.asList(cat4));
        p8.getCategorias().addAll(Arrays.asList(cat5));
        p9.getCategorias().addAll(Arrays.asList(cat6));
        p10.getCategorias().addAll(Arrays.asList(cat6));
        p11.getCategorias().addAll(Arrays.asList(cat7));

        est1.getCidades().addAll(Arrays.asList(c1));
        est2.getCidades().addAll(Arrays.asList(c2, c3));

        serviceCategoria.saveList(Arrays.asList(cat1,
                cat2,
                cat3,
                cat4,
                cat5,
                cat6,
                cat7,
                cat8,
                cat9,
                cat10,
                cat11,
                cat12,
                cat13,
                cat14,
                cat15,
                cat16,
                cat17,
                cat18,
                cat19,
                cat20));
        serviceProduto.saveList(Arrays.asList(pro1, pro2, pro3, p4, p5, p6, p7, p8, p9, p10, p11));
        serviceEstado.saveList(Arrays.asList(est1, est2));
        serviceCidade.saveList(Arrays.asList(c1, c2, c3));

        Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
        cli1.getTelefones().addAll(Arrays.asList("32763323", "93838393"));

        Endereco e1 = new Endereco(null, "Rua flores", "300", "Ap 203", "Jardim", "3820834", cli1, c1);
        Endereco e2 = new Endereco(null, "Avenida matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);

        cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

        serviceCliente.saveList(Arrays.asList(cli1));
        serviceEndereco.saveLit(Arrays.asList(e1, e2));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
        Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);

        PagamentoComCartao pagto1 = new PagamentoComCartao(6, null, EstadoPagamento.QUITADO, ped1);
        PagamentoComBoleto pagto2 = new PagamentoComBoleto(sdf.parse("20/10/2017 00:00"), null, null, EstadoPagamento.PENDENTE, ped2);

        ped1.setPagamento(pagto1);
        ped2.setPagamento(pagto2);

        cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

        servicePedido.saveList(Arrays.asList(ped1, ped2));
        servicePagamento.saveList(Arrays.asList(pagto2, pagto1));

        ItemPedido ip1 = new ItemPedido(ped1, pro1, 0.00, 1, 2000.00);
        ItemPedido ip2 = new ItemPedido(ped1, pro3, 0.00, 2, 80.00);
        ItemPedido ip3 = new ItemPedido(ped2, pro2, 100.00, 1, 800.00);

        ped1.getItens().addAll(Arrays.asList(ip1, ip2));
        ped2.getItens().addAll(Arrays.asList(ip3));
        pro1.getItens().addAll(Arrays.asList(ip1));
        pro2.getItens().addAll(Arrays.asList(ip3));
        pro3.getItens().addAll(Arrays.asList(ip2));

        serviceItemPedido.saveList(Arrays.asList(ip1, ip2, ip3));
    }
}
