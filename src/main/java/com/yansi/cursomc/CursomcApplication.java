package com.yansi.cursomc;

import com.yansi.cursomc.domain.Categoria;
import com.yansi.cursomc.domain.Produto;
import com.yansi.cursomc.servives.CategoriaServices;
import com.yansi.cursomc.servives.ProdutoServices;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

    @Autowired
    CategoriaServices serviceCategoria;

    @Autowired
    ProdutoServices serviceProduto;

    public static void main(String[] args) {
        SpringApplication.run(CursomcApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        Categoria cat1 = new Categoria(null, "Informatica");
        Categoria cat2 = new Categoria(null, "Escritorio");

        Produto pro1 = new Produto(null, "Computador", 2000.00);
        Produto pro2 = new Produto(null, "Impressora", 800.00);
        Produto pro3 = new Produto(null, "Mouse", 80.00);

        cat1.getProdutos().addAll(Arrays.asList(pro1, pro2, pro3));
        cat2.getProdutos().addAll(Arrays.asList(pro2));

        pro1.getCategorias().addAll(Arrays.asList(cat1));
        pro2.getCategorias().addAll(Arrays.asList(cat1, cat2));
        pro3.getCategorias().addAll(Arrays.asList(cat1));

        serviceCategoria.saveList(Arrays.asList(cat1, cat2));
        serviceProduto.saveList(Arrays.asList(pro1, pro2, pro3));

    }
}
