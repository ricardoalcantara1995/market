package com.rlaal.minimarket.service;

import com.rlaal.minimarket.dto.response.ProductoResponseDTO;
import com.rlaal.minimarket.entity.Producto;

import java.util.List;
import java.util.UUID;

public interface ProductoService {
    List<ProductoResponseDTO> listarProductos();
    ProductoResponseDTO buscarProducto(UUID id);
}
