package com.rlaal.minimarket.service.impl;

import com.rlaal.minimarket.dto.response.ProductoResponseDTO;
import com.rlaal.minimarket.entity.Producto;
import com.rlaal.minimarket.exception.ResourceNotFoundException;
import com.rlaal.minimarket.repository.ProductoRepository;
import com.rlaal.minimarket.service.ProductoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductoServiceImpl implements ProductoService {
    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository){
        this.productoRepository = productoRepository;
    }


    @Override
    public List<ProductoResponseDTO> listarProductos() {
        return productoRepository.findAllActiveWithCategory()
                .stream()
                .map(prod ->  ProductoResponseDTO.builder()
                        .id(prod.getId())
                        .stock(prod.getStock())
                        .precio(prod.getPrecio())
                        .descripcion(prod.getDescripcion())
                        .nombre(prod.getNombre())
                        .nombreCategoria(prod.getCategoria().getNombre())
                        .build()
                ).toList();

    }

    @Override
    public ProductoResponseDTO buscarProducto(UUID id) {
        return productoRepository.findByIdActiveWithCategory(id)
                .map(prod ->  ProductoResponseDTO.builder()
                        .id(prod.getId())
                        .stock(prod.getStock())
                        .precio(prod.getPrecio())
                        .descripcion(prod.getDescripcion())
                        .nombre(prod.getNombre())
                        .nombreCategoria(prod.getCategoria().getNombre())
                        .activo(prod.isActivo())
                        .build())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Producto no existe o no encontrado")
                );
    }


}
