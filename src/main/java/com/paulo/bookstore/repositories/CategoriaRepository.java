package com.paulo.bookstore.repositories;

import com.paulo.bookstore.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    @Query("SELECT obj.nome FROM Categoria obj WHERE obj.id = :id_cat")
    String findNameCategoria(@Param("id_cat") Integer id_cat);
}
