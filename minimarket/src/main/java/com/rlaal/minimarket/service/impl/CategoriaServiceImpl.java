package com.rlaal.minimarket.service.impl;

import com.rlaal.minimarket.dto.request.CategoriaRequestDTO;
import com.rlaal.minimarket.dto.response.CategoriaResponseDTO;
import com.rlaal.minimarket.dto.response.MessageResponseDTO;
import com.rlaal.minimarket.entity.Categoria;
import com.rlaal.minimarket.exception.DuplicateResourceException;
import com.rlaal.minimarket.exception.ResourceNotFoundException;
import com.rlaal.minimarket.repository.CategoriaRepository;
import com.rlaal.minimarket.service.CategoriaService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        return categoriaRepository.findAllByActivoTrueOrderByNombreAsc()
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
    @Transactional
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

    @Override
    @Transactional
    public CategoriaResponseDTO editarCategoria(UUID uuid, CategoriaRequestDTO categoriaRequestDTO) {
        Categoria categoria = categoriaRepository.findById(uuid).
                orElseThrow(
                        () -> new ResourceNotFoundException("Categoria no existe o no se encontro")
                );
        String nombreLimpio = categoriaRequestDTO.getNombre();
        nombreLimpio = nombreLimpio.trim();

        boolean respuesta = categoriaRepository. existsByNombreAndIdNot(nombreLimpio,uuid);
        if(respuesta){
            throw  new DuplicateResourceException("Categoria ya existe");
        }

        categoria.setNombre(nombreLimpio);
        categoria.setFechaActualizacion( LocalDateTime.now());

        return new CategoriaResponseDTO(categoria.getId(),categoria.getNombre());
    }

    @Override
    @Transactional
    public MessageResponseDTO eliminarCategoria(UUID uuid) {
        Categoria categoria = categoriaRepository.findById(uuid)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Categoria no encontrada")
                );
        categoria.setActivo(false);
        return new MessageResponseDTO(HttpStatus.OK,"Categoria eliminada");
    }


}
