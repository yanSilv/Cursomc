/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yansi.cursomc.servives;

import com.yansi.cursomc.domain.Cliente;
import com.yansi.cursomc.dto.ClienteDTO;
import com.yansi.cursomc.enums.TipoCliente;
import com.yansi.cursomc.error.ObjectNotFoundException;
import com.yansi.cursomc.repositories.ClienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
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

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);

        return repo.findAll(pageRequest);
    }

    public List<Cliente> findAll() {
        return repo.findAll();
    }

    public Cliente fromDTO(ClienteDTO objDto) {
        return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);

    }

    public void delete(Integer id) {
        buscar(id);
        repo.delete(id);
    }

    public Cliente update(Cliente obj) {

        Cliente newObj = buscar(obj.getId());
        updateData(newObj, obj);
        return repo.save(obj);
    }

    private void updateData(Cliente newObj, Cliente obj) {
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }
}
