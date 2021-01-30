package com.paulo.bookstore.dtos;

import com.paulo.bookstore.domain.Categoria;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class CategoriaDTO implements Serializable {
    private Integer id;

    @NotEmpty(message = "O Campo NOME é requerido!")
    @Length(min = 5, max = 100, message = "O Campo NOME deve ter entre 5 e 100 caracteres!")
    private String nome;

    @NotEmpty(message = "O Campo DESCRIÇÃO é requerido!")
    @Length(min = 5, max = 200, message = "O Campo DESCRIÇÃO deve ter entre 5 e 200 caracteres!")
    private String descricao;

    public CategoriaDTO() {
    }

    public CategoriaDTO(Categoria obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.descricao = obj.getDescricao();
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
