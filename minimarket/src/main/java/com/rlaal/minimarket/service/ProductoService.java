package com.rlaal.minimarket.service;

import com.rlaal.minimarket.dto.response.ProductoResponseDTO;
import com.rlaal.minimarket.entity.Producto;

import java.util.List;

public interface ProductoService {
    List<ProductoResponseDTO> listarProductos();
}
