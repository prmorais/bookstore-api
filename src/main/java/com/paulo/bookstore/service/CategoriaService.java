package com.paulo.bookstore.service;

import com.paulo.bookstore.domain.Categoria;
import com.paulo.bookstore.repositories.CategoriaRepository;
import com.paulo.bookstore.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Autowired
    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Categoria findById(Integer id) {
        Optional<Categoria> obj = categoriaRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
            "Objeto não encontrado para o ID " + id + ", Tipo " + Categoria.class.getName()));
    }

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public Categoria create(Categoria obj) {
        obj.setId(null);
        return categoriaRepository.save(obj);
    }
}
