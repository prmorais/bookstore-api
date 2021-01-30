package com.paulo.bookstore.service;

import com.paulo.bookstore.domain.Categoria;
import com.paulo.bookstore.domain.Livro;
import com.paulo.bookstore.dtos.LivroDTO;
import com.paulo.bookstore.repositories.CategoriaRepository;
import com.paulo.bookstore.repositories.LivroRepository;
import com.paulo.bookstore.service.exception.IntegrityViolationException;
import com.paulo.bookstore.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    private final LivroRepository livroRepository;
    private final CategoriaService categoriaService;

    @Autowired
    public LivroService(LivroRepository livroRepository, CategoriaService categoriaService) {
        this.livroRepository = livroRepository;
        this.categoriaService = categoriaService;
    }

    public Livro findById(Integer id) {
        Optional<Livro> obj = livroRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
            "Livro não encontrado para o ID " + id + ", Tipo " + Livro.class.getName()));
    }

    public List<Livro> findAll() {
        return livroRepository.findAll();
    }

    public List<Livro> findAllByCategoria(Integer id_cat) {
        categoriaService.findById(id_cat);
        return livroRepository.findAllByCategoria(id_cat);
    }

    public Livro create(Integer id_cat, Livro obj) {
        obj.setId(null);
        Categoria cat = categoriaService.findById(id_cat);
        obj.setCategoria(cat);
        return livroRepository.save(obj);
    }

    public Livro update(Integer id, Livro obj) {
        Livro newObj = findById(id);
        updateData(newObj, obj);
        return livroRepository.save(newObj);
    }

    private void updateData(Livro newObj, Livro obj) {
        newObj.setTitulo(obj.getTitulo());
        newObj.setNome_autor(obj.getNome_autor());
        newObj.setTexto(obj.getTexto());
    }

    public void delete(Integer id) {
        findById(id);
        try {
            livroRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new IntegrityViolationException("O Livro não pode ser removido pois está associado a uma Categoria.");
        }
    }
}
