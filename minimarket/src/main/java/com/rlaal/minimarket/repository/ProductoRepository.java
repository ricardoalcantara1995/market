package com.rlaal.minimarket.repository;

import com.rlaal.minimarket.dto.response.ProductoResponseDTO;
import com.rlaal.minimarket.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, UUID> {
    @Query("Select p From Producto p JOIN FETCH p.categoria WHERE p.activo = true order by p.nombre")
    List<Producto>findAllActiveWithCategory();
    @Query("Select p From Producto p JOIN FETCH p.categoria " +
            "WHERE p.activo = true AND p.id = :id")
    Optional<Producto> findByIdActiveWithCategory(@Param("id") UUID id);

    boolean existsByNombreIgnoreCase(String nombre);
}
