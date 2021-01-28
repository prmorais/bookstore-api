package com.paulo.bookstore.service;

import com.paulo.bookstore.domain.Livro;
import com.paulo.bookstore.dtos.LivroDTO;
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

    @Autowired
    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public Livro findById(Integer id) {
        Optional<Livro> obj = livroRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
            "Livro não encontrado para o ID " + id + ", Tipo " + Livro.class.getName()));
    }

    public List<Livro> findAll() {
        return livroRepository.findAll();
    }

    public Livro create(Livro obj) {
        obj.setId(null);
        return livroRepository.save(obj);
    }

    public Livro update(Integer id, LivroDTO objDto) {
        Livro obj = findById(id);
        obj.setNome_autor(objDto.getNome_autor());
        obj.setTexto(objDto.getTexto());
        obj.setTitulo(objDto.getTitulo());
        return livroRepository.save(obj);
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
