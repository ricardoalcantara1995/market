package com.rlaal.minimarket.service;

import com.rlaal.minimarket.dto.request.CategoriaRequestDTO;
import com.rlaal.minimarket.dto.response.CategoriaResponseDTO;
import com.rlaal.minimarket.dto.response.MessageResponseDTO;
import com.rlaal.minimarket.entity.Categoria;

import java.util.List;
import java.util.UUID;

public interface CategoriaService {
    List<CategoriaResponseDTO>listarCategirias();
    CategoriaResponseDTO buscarCategoria(UUID uuid);
    CategoriaResponseDTO crearCategoria(CategoriaRequestDTO categoriaRequestDTO);
    CategoriaResponseDTO editarCategoria(UUID uuid, CategoriaRequestDTO categoriaRequestDTO);
    MessageResponseDTO eliminarCategoria (UUID uuid);
 }
