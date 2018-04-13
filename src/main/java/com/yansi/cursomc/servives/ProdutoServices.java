/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yansi.cursomc.servives;

import com.yansi.cursomc.domain.Produto;
import com.yansi.cursomc.repositories.ProdutoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoServices {

    @Autowired
    private ProdutoRepository repo;

    public void saveList(List<Produto> asList) {
        repo.save(asList);
    }

}
