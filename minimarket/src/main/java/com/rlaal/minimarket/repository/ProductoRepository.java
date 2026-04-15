package com.rlaal.minimarket.repository;

import com.rlaal.minimarket.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, UUID> {
    @Query("Select p From Producto p JOIN FETCH p.categoria WHERE p.activo = true order by p.nombre")
    List<Producto>findAllWithCategoria();
}
