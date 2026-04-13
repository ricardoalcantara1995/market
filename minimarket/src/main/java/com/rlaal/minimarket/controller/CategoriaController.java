package com.rlaal.minimarket.controller;

import com.rlaal.minimarket.dto.request.CategoriaRequestDTO;
import com.rlaal.minimarket.dto.response.CategoriaResponseDTO;
import com.rlaal.minimarket.dto.response.MessageResponseDTO;
import com.rlaal.minimarket.entity.Categoria;
import com.rlaal.minimarket.service.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/categoria")
public class CategoriaController {
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }
    @GetMapping("/listar")
    ResponseEntity<List<CategoriaResponseDTO>>ListarCategoria(){
        return ResponseEntity.status(HttpStatus.OK).body(categoriaService.listarCategirias());
    }
    @GetMapping("/listar-inactivas")
    ResponseEntity<List<CategoriaResponseDTO>>ListarCategoriaInactivas(){
        return ResponseEntity.status(HttpStatus.OK).body(categoriaService.listarCategiriasInactivas());
    }

    @GetMapping("/{uuid}")
    ResponseEntity<CategoriaResponseDTO>buscarCategoria(@PathVariable UUID uuid){
        return ResponseEntity.status(HttpStatus.OK).body(categoriaService.buscarCategoria(uuid));
    }

    @PostMapping("/nueva")
    ResponseEntity<CategoriaResponseDTO>crearCategoria(@RequestBody CategoriaRequestDTO dto){
       return  ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.crearCategoria(dto));
    }
    @PutMapping("/editar/{uuid}")
    ResponseEntity<CategoriaResponseDTO>editarCategoria(@PathVariable UUID uuid,@RequestBody CategoriaRequestDTO  dto ){
        return ResponseEntity.status(HttpStatus.OK).body(categoriaService.editarCategoria(uuid,dto));

    }
   @DeleteMapping("/eliminar/{uuid}")
   ResponseEntity<MessageResponseDTO>eliminarCategoria(@PathVariable UUID uuid){
       return ResponseEntity.status(HttpStatus.OK).body(categoriaService.eliminarCategoria(uuid));
   }
}
