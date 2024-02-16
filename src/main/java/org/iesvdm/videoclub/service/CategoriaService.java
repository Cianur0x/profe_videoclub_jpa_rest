package org.iesvdm.videoclub.service;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.videoclub.domain.Categoria;
import org.iesvdm.videoclub.exception.CategoriaNotFoundException;
import org.iesvdm.videoclub.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> all() {
        return this.categoriaRepository.findAll();
    }

    public Categoria save(Categoria categoria) {
        return this.categoriaRepository.save(categoria);
    }

    public Categoria one(Long id) {
        return this.categoriaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNotFoundException(id));
    }

    public Categoria replace(Long id, Categoria categoria) {
        log.info("CATEGORIA 2" + categoria);
        return this.categoriaRepository.findById(id).map(p -> (id.equals(categoria.getId()) ?
                        this.categoriaRepository.save(categoria) : null))
                .orElseThrow(() -> new CategoriaNotFoundException(id));
    }

    public void delete(Long id) {
        this.categoriaRepository.findById(id).map(p -> {
                    this.categoriaRepository.delete(p);
                    return p;
                })
                .orElseThrow(() -> new CategoriaNotFoundException(id));
    }

}
