/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yansi.cursomc.servives;

import com.yansi.cursomc.domain.Categoria;
import com.yansi.cursomc.error.DataIntegrityException;
import com.yansi.cursomc.error.ObjectNotFoundException;
import com.yansi.cursomc.repositories.CategoriaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repo;

    public Categoria buscar(Integer id) {
        Categoria obj = repo.findOne(id);

        if (obj == null) {
            throw new ObjectNotFoundException("Objeto não encontrado! ID: " + id + " Tipo: " + Categoria.class.getName());
        }

        return obj;
    }

    public List<Categoria> findAll() {
        List<Categoria> list = repo.findAll();
        return list;
    }

    public void saveList(List<Categoria> asList) {
        repo.save(asList);
    }

    public Categoria insert(Categoria obj) {

        return repo.save(obj);
    }

    public Categoria update(Categoria obj) {
        buscar(obj.getId());
        return repo.save(obj);
    }

    public void delete(Integer id) {
        buscar(id);
        try {

            repo.delete(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possivel deletar uma Categoria com produto");
        }

    }
}
