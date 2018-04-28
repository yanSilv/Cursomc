/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yansi.cursomc.services;

import com.yansi.cursomc.domain.Pedido;
import com.yansi.cursomc.error.ObjectNotFoundException;
import com.yansi.cursomc.repositories.PedidoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repo;

    public void saveList(List<Pedido> asList) {
        repo.save(asList);
    }

    public Pedido bucar(Integer id) {
        Pedido obj = repo.findOne(id);

        if (obj == null) {
            throw new ObjectNotFoundException("Objeto não encontrado! ID: " + id + " Tipo: " + Pedido.class.getName());
        }

        return obj;
    }

}
