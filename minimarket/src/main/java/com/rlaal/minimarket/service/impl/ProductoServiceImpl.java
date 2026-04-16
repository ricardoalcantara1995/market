package com.rlaal.minimarket.service.impl;

import com.rlaal.minimarket.dto.request.ProductoRequestDTO;
import com.rlaal.minimarket.dto.response.ProductoResponseDTO;
import com.rlaal.minimarket.entity.Categoria;
import com.rlaal.minimarket.entity.Producto;
import com.rlaal.minimarket.exception.DuplicateResourceException;
import com.rlaal.minimarket.exception.ResourceNotFoundException;
import com.rlaal.minimarket.repository.CategoriaRepository;
import com.rlaal.minimarket.repository.ProductoRepository;
import com.rlaal.minimarket.service.ProductoService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductoServiceImpl implements ProductoService {
    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;


    public ProductoServiceImpl(ProductoRepository productoRepository, CategoriaRepository categoriaRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
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

    @Override
    @Transactional
    public ProductoResponseDTO crearProducto(ProductoRequestDTO productoRequestDTO) {
        String nombreIngresado = productoRequestDTO.getNombre();
        nombreIngresado = nombreIngresado.trim();
        boolean respuesta = productoRepository.existsByNombreIgnoreCase(nombreIngresado);
        if(respuesta){
                throw new DuplicateResourceException("Nombre del producto ya en uso");
        }

        Categoria categoria =  categoriaRepository.findById(productoRequestDTO.getCategoriaId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Categoria no encontrada")
                );

        if(!categoria.isActivo()){
            throw new DuplicateResourceException("Categoria ingresada fuera de servicio ");
        }

        Producto nuevoProducto = new Producto(
                nombreIngresado,
                productoRequestDTO.getDescripcion(),
                productoRequestDTO.getPrecio(),
                productoRequestDTO.getStock(),
                categoria
        );
        Producto guardado =  productoRepository.save(nuevoProducto);

        return ProductoResponseDTO.builder()
                .id(guardado.getId())
                .nombre(guardado.getNombre())
                .descripcion(guardado.getDescripcion())
                .precio(guardado.getPrecio())
                .stock(guardado.getStock())
                .nombreCategoria(guardado.getCategoria().getNombre())
                .build();

    }


}
