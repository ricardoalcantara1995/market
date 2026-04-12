package com.rlaal.minimarket.repository;

import com.rlaal.minimarket.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, UUID> {
    List<Categoria>findAllByOrderByNombreAsc();
    boolean existsByNombre(String name);
    boolean existsByNombreAndIdNot(String nombre, UUID id);
}
