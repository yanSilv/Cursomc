/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yansi.cursomc.services;

import com.yansi.cursomc.domain.Cidade;
import com.yansi.cursomc.domain.Cliente;
import com.yansi.cursomc.domain.Endereco;
import com.yansi.cursomc.dto.ClienteDTO;
import com.yansi.cursomc.dto.ClienteNewDTO;
import com.yansi.cursomc.enums.TipoCliente;
import com.yansi.cursomc.error.DataIntegrityException;
import com.yansi.cursomc.error.ObjectNotFoundException;
import com.yansi.cursomc.repositories.ClienteRepository;
import com.yansi.cursomc.repositories.EnderecoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private BCryptPasswordEncoder encode;

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

    public Cliente fromDTO(ClienteNewDTO objDto) {
        Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()), encode.encode(objDto.getSenha()));
        Cidade cid = new Cidade(objDto.getCaidadeId(), null, null);
        Endereco end = new Endereco(null, objDto.getLougradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
        cli.getEnderecos().add(end);
        cli.getTelefones().add(objDto.getTelefone1());

        if (objDto.getTelefone2() != null) {
            cli.getTelefones().add(objDto.getTelefone2());
        }

        if (objDto.getTelefone3() != null) {
            cli.getTelefones().add(objDto.getTelefone3());
        }

        return cli;
    }

    public Cliente fromDTO(ClienteDTO objDto) {
        return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null, null);
    }

    public void delete(Integer id) {
        buscar(id);
        try {
            repo.delete(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possivel deletar um Cliente com pedido");
        }
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

    @Transactional
    public Cliente insert(Cliente obj) {
        obj.setId(null);
        obj = repo.save(obj);
        enderecoRepository.save(obj.getEnderecos());
        return obj;
    }
}
