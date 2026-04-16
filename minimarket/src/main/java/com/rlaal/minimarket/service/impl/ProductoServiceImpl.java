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

import java.time.LocalDateTime;
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



    @Override
    @Transactional
    public ProductoResponseDTO editarProducto(UUID id, ProductoRequestDTO productoRequestDTO) {
           Producto producto = productoRepository.findByIdActiveWithCategory(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("No se encontró el producto con ID: "+ id)
                );



        String nombreLimpio  = productoRequestDTO.getNombre();
        nombreLimpio = nombreLimpio.trim();

        boolean respuesta = productoRepository.existsByNombreIgnoreCaseAndIdNot(nombreLimpio,id);
        if(respuesta){
            throw new DuplicateResourceException("El nombre del producto ya en uso");
        }
        boolean cambioCategoria = producto.getCategoria().getId().equals(productoRequestDTO.getCategoriaId());

        if(!cambioCategoria){
            Categoria categoria = categoriaRepository.findById(productoRequestDTO.getCategoriaId())
                    .orElseThrow(
                            () -> new ResourceNotFoundException("La categoria no existe")
                    );
            boolean estadoCategoria = categoria.isActivo();
            if(!estadoCategoria){
                throw  new DuplicateResourceException("Categoria no habilitada");
            }
            producto.setCategoria(categoria);
        }

        producto.setNombre(productoRequestDTO.getNombre());
        producto.setDescripcion(productoRequestDTO.getDescripcion());
        producto.setPrecio(productoRequestDTO.getPrecio());
        producto.setStock(productoRequestDTO.getStock());
        producto.setFechaActualizacion(LocalDateTime.now());
        Producto productoActualizado = productoRepository.save(producto);

        ProductoResponseDTO respuestaActualizacion =  ProductoResponseDTO.builder()
                .id(id)
                .nombre(productoActualizado.getNombre())
                .descripcion(productoActualizado.getDescripcion())
                .precio(productoActualizado.getPrecio())
                .stock(productoActualizado.getStock())
                .nombreCategoria(productoActualizado.getCategoria().getNombre())
                .build();


        return respuestaActualizacion;
    }


}
