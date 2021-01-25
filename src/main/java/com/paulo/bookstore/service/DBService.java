package com.paulo.bookstore.service;

import com.paulo.bookstore.domain.Categoria;
import com.paulo.bookstore.domain.Livro;
import com.paulo.bookstore.repositories.CategoriaRepository;
import com.paulo.bookstore.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Arrays.asList;

@Service
public class DBService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private LivroRepository livroRepository;

    public void instanciaBaseDeDados() {
        Categoria cat1 = new Categoria(null, "Informática", "Livro de T.I.");
        Categoria cat2 = new Categoria(null, "Ficção científica", "Livro Ficção científica");
        Categoria cat3 = new Categoria(null, "Biografias", "Livro Biografias");

        Livro liv1 = new Livro(null, "Clean code", "Paulo Roberto", "Lorem ipsum", cat1);
        Livro liv2 = new Livro(null, "Engenharia de software", "Patrícia Nunes", "Lorem ipsum", cat1);
        Livro liv3 = new Livro(null, "Meu pé de laranja lima", "Maria Fernanda", "Lorem ipsum", cat3);
        Livro liv4 = new Livro(null, "Guerra nas estrelas", "Paulo Ricardo", "Lorem ipsum", cat2);
        Livro liv5 = new Livro(null, "React com hooks", "Ana Paula", "Lorem ipsum", cat1);

        cat1.getLivros().addAll(asList(liv1, liv2, liv5));
        cat2.getLivros().add(liv4);
        cat3.getLivros().add(liv3);

        categoriaRepository.saveAll(asList(cat1, cat2, cat3));
        livroRepository.saveAll(asList(liv1, liv2, liv3, liv4, liv5));
    }
}
