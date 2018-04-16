/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yansi.cursomc.servives;

import com.yansi.cursomc.domain.Cliente;
import com.yansi.cursomc.error.ObjectNotFoundException;
import com.yansi.cursomc.repositories.ClienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    public void saveList(List<Cliente> asList) {
        repo.save(asList);
    }

    public Cliente buscar(Integer id) {
        Cliente obj = repo.findOne(id);

        if (obj == null) {
            throw new ObjectNotFoundException("Objeto não encontrado! ID: " + id + " Tipo: " + Cliente.class.getName());
        }

        return obj;
    }

}
