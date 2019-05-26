/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yansi.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yansi.cursomc.domain.Estado;
import com.yansi.cursomc.repositories.EstadoRepository;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository repo;

    public void saveList(List<Estado> asList) {
        repo.save(asList);
    }
    
    public List<Estado> findAllOrderNome() {
    	return repo.findAllByOrderByNome();
    }
}
