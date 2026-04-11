package com.rlaal.minimarket.service.impl;

import com.rlaal.minimarket.dto.request.CategoriaRequestDTO;
import com.rlaal.minimarket.dto.response.CategoriaResponseDTO;
import com.rlaal.minimarket.entity.Categoria;
import com.rlaal.minimarket.exception.DuplicateResourceException;
import com.rlaal.minimarket.exception.ResourceNotFoundException;
import com.rlaal.minimarket.repository.CategoriaRepository;
import com.rlaal.minimarket.service.CategoriaService;
import org.springframework.stereotype.Service;

import java.util.List;
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

    @Override
    public CategoriaResponseDTO crearCategoria(CategoriaRequestDTO categoriaRequestDTO) {
            String nombreCategoria  = categoriaRequestDTO.getNombre();
            nombreCategoria = nombreCategoria.trim();

            if(categoriaRepository.existsByNombre(nombreCategoria)){
                    throw  new DuplicateResourceException("Categoria ya existe");
            }
         Categoria categoria = new Categoria(nombreCategoria);
         Categoria guardado = categoriaRepository.save(categoria);

        return new CategoriaResponseDTO(guardado.getId(),guardado.getNombre());
    }


}
