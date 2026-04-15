package com.rlaal.minimarket.controller;

import com.rlaal.minimarket.dto.response.CategoriaResponseDTO;
import com.rlaal.minimarket.dto.response.ProductoResponseDTO;
import com.rlaal.minimarket.entity.Producto;
import com.rlaal.minimarket.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/producto")
public class ProductoController {
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/listar")
    ResponseEntity<List<ProductoResponseDTO>>listarProductos(){
       return ResponseEntity.status(HttpStatus.OK).body(productoService.listarProductos());
    }
    @GetMapping("/{uuid}")
    ResponseEntity<ProductoResponseDTO>buscarProducto(@PathVariable UUID uuid){
        return ResponseEntity.status(HttpStatus.OK).body(productoService.buscarProducto(uuid));
    }
}
