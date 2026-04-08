package com.rlaal.minimarket.service.impl;

import com.rlaal.minimarket.entity.Categoria;
import com.rlaal.minimarket.repository.CategoriaRepository;
import com.rlaal.minimarket.service.CategoriaService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoriaServiceImpl implements CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<Categoria> listarCategirias() {
        return categoriaRepository.findAll();
    }
}
