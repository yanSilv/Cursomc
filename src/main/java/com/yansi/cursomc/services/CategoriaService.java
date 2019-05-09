/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yansi.cursomc.services;

import com.yansi.cursomc.domain.Categoria;
import com.yansi.cursomc.dto.CategoriaDTO;
import com.yansi.cursomc.error.DataIntegrityException;
import com.yansi.cursomc.error.ObjectNotFoundException;
import com.yansi.cursomc.repositories.CategoriaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
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
        Categoria newObj = buscar(obj.getId());
        updateData(newObj, obj);
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

    public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    public Categoria fromDTO(CategoriaDTO objDto) {
        return new Categoria(objDto.getId(), objDto.getNome());
    }

    private void updateData(Categoria newObj, Categoria obj) {
        newObj.setNome(obj.getNome());
    }
}
