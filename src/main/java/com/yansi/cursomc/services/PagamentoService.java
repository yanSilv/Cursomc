/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yansi.cursomc.services;

import com.yansi.cursomc.domain.Pagamento;
import com.yansi.cursomc.repositories.PagamentoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository repo;

    public void saveList(List<Pagamento> asList) {
        repo.save(asList);
    }
}
