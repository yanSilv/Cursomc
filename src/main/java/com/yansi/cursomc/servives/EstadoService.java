/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yansi.cursomc.servives;

import com.yansi.cursomc.domain.Categoria;
import com.yansi.cursomc.domain.Estado;
import com.yansi.cursomc.repositories.EstadoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository repo;

    public void saveList(List<Estado> asList) {
        repo.save(asList);
    }
}
