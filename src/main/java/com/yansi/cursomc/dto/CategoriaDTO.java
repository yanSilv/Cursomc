/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yansi.cursomc.dto;

import com.yansi.cursomc.domain.Categoria;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class CategoriaDTO {

    private Integer id;

    @NotEmpty(message = "Preenchimento obrigatorio")
    @Length(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80")
    private String nome;

    public CategoriaDTO() {
    }

    public CategoriaDTO(Categoria obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
