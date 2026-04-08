package com.rlaal.minimarket.controller;

import com.rlaal.minimarket.entity.Categoria;
import com.rlaal.minimarket.service.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categoria")
public class CategoriaController {
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }
    @GetMapping("/listar")
    ResponseEntity<List<Categoria>>ListarCategoria(){
        return ResponseEntity.status(HttpStatus.OK).body(categoriaService.listarCategirias());
    }



}
