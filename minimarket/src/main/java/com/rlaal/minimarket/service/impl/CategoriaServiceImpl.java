package com.rlaal.minimarket.service.impl;

import com.rlaal.minimarket.dto.request.CategoriaRequestDTO;
import com.rlaal.minimarket.dto.response.CategoriaResponseDTO;
import com.rlaal.minimarket.entity.Categoria;
import com.rlaal.minimarket.exception.ResourceNotFoundException;
import com.rlaal.minimarket.repository.CategoriaRepository;
import com.rlaal.minimarket.service.CategoriaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoriaServiceImpl implements CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }


    @Override
    public List<CategoriaResponseDTO> listarCategirias() {
        return categoriaRepository.findAllByOrderByNombreAsc()
                .stream()
                .map(cat -> new CategoriaResponseDTO(cat.getId(),cat.getNombre()))
                .toList();
    }

    @Override
    public CategoriaResponseDTO buscarCategoria(UUID uuid) {
        return categoriaRepository.findById(uuid)
                .map(cat -> new CategoriaResponseDTO(cat.getId(),cat.getNombre()))
                .orElseThrow(
                        () -> new ResourceNotFoundException("Categoria no existe o no se encontro")
                );
    }


}
