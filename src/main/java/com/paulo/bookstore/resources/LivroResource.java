package com.paulo.bookstore.resources;

import com.paulo.bookstore.domain.Livro;
import com.paulo.bookstore.dtos.LivroDTO;
import com.paulo.bookstore.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/livros")
public class LivroResource {

    private final LivroService livroService;

    @Autowired
    public LivroResource(LivroService livroService) {
        this.livroService = livroService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Livro> findById(@PathVariable Integer id) {
        Livro obj = livroService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

//    @GetMapping
//    public ResponseEntity<List<LivroDTO>> findAll() {
//        List<Livro> list = livroService.findAll();
//        List<LivroDTO> listDTO = list.stream().map(LivroDTO::new).collect(Collectors.toList());
//        return ResponseEntity.ok(listDTO);
//    }

    @GetMapping
    public ResponseEntity<List<LivroDTO>> findAllByCategoria(
        @RequestParam(value = "categoria", defaultValue = "0") Integer id_cat
    ) {
        List<Livro> list = livroService.findAllByCategoria(id_cat);
        List<LivroDTO> listDTO = list.stream().map(LivroDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(listDTO);
    }

    @PostMapping
    public ResponseEntity<Livro> create (
            @RequestParam(value = "categoria", defaultValue = "0") Integer id_cat, @Valid @RequestBody Livro obj
    ) {
        Livro newObj = livroService.create(id_cat, obj);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newObj.getId())
                .toUri();
        return ResponseEntity.created(uri).body(newObj);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Livro> update(@PathVariable Integer id, @Valid @RequestBody Livro obj) {
        Livro newObj = livroService.update(id, obj);
        return ResponseEntity.ok(newObj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        livroService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
