package com.yansi.cursomc;

import com.yansi.cursomc.domain.Categoria;
import com.yansi.cursomc.domain.Cidade;
import com.yansi.cursomc.domain.Estado;
import com.yansi.cursomc.domain.Produto;
import com.yansi.cursomc.servives.CategoriaService;
import com.yansi.cursomc.servives.CidadeService;
import com.yansi.cursomc.servives.EstadoService;
import com.yansi.cursomc.servives.ProdutoServices;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

    @Autowired
    CategoriaService serviceCategoria;

    @Autowired
    ProdutoServices serviceProduto;

    @Autowired
    EstadoService serviceEstado;

    @Autowired
    CidadeService serviceCidade;

    public static void main(String[] args) {
        SpringApplication.run(CursomcApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {

        //Inicializa Categoria
        Categoria cat1 = new Categoria(null, "Informatica");
        Categoria cat2 = new Categoria(null, "Escritorio");

        //Inicializa Produto
        Produto pro1 = new Produto(null, "Computador", 2000.00);
        Produto pro2 = new Produto(null, "Impressora", 800.00);
        Produto pro3 = new Produto(null, "Mouse", 80.00);

        //Inicializa Estado
        Estado est1 = new Estado(null, "Minhas Gerais");
        Estado est2 = new Estado(null, "São Paulo");

        //Inicializa Cidade
        Cidade c1 = new Cidade(null, "Uberlandia", est1);
        Cidade c2 = new Cidade(null, "São Paulo", est2);
        Cidade c3 = new Cidade(null, "Campinas", est2);

        cat1.getProdutos().addAll(Arrays.asList(pro1, pro2, pro3));
        cat2.getProdutos().addAll(Arrays.asList(pro2));

        pro1.getCategorias().addAll(Arrays.asList(cat1));
        pro2.getCategorias().addAll(Arrays.asList(cat1, cat2));
        pro3.getCategorias().addAll(Arrays.asList(cat1));

        est1.getCidades().addAll(Arrays.asList(c1));
        est2.getCidades().addAll(Arrays.asList(c2, c3));

        serviceCategoria.saveList(Arrays.asList(cat1, cat2));
        serviceProduto.saveList(Arrays.asList(pro1, pro2, pro3));
        serviceEstado.saveList(Arrays.asList(est1, est2));
        serviceCidade.saveList(Arrays.asList(c1, c2, c3));

    }
}
