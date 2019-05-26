/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yansi.cursomc.services;

import com.yansi.cursomc.domain.Cidade;
import com.yansi.cursomc.domain.Estado;
import com.yansi.cursomc.repositories.CidadeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository repo;

    public void saveList(List<Cidade> asList) {
        repo.save(asList);
    }
    
    public List<Cidade> findCidades(Integer estadoId) {
    	return repo.findCidades(estadoId);
    }

}
