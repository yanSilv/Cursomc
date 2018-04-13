/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yansi.cursomc.servives;

import com.yansi.cursomc.domain.Categoria;
import com.yansi.cursomc.error.ObjectNotFoundException;
import com.yansi.cursomc.repositories.CategoriaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaServices {

    @Autowired
    private CategoriaRepository repo;

    public Categoria bucar(Integer id) {
        Categoria obj = repo.findOne(id);

        if (obj == null) {
            throw new ObjectNotFoundException("Objeto não encontrado! ID: " + id + " Tipo: " + Categoria.class.getName());
        }

        return obj;
    }

    public void saveList(List<Categoria> asList) {
        repo.save(asList);
    }
}
