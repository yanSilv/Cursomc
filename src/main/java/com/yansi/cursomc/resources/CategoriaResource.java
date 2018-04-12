/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yansi.cursomc.resources;

import com.yansi.cursomc.domain.Categoria;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @GetMapping
    public List<Categoria> listar() {

        Categoria cat1 = new Categoria(1, "Java");
        Categoria cat2 = new Categoria(2, "CSS");

        List<Categoria> catList = new ArrayList<>();
        catList.add(cat1);
        catList.add(cat2);

        return catList;
    }
}
