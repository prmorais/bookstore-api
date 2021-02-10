package com.paulo.bookstore.dtos;

import com.paulo.bookstore.domain.Categoria;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class CategoriaNomeDTO implements Serializable {
    private String nome;

    public CategoriaNomeDTO() {
    }

    public CategoriaNomeDTO(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
