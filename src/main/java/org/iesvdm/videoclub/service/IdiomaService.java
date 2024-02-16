package org.iesvdm.videoclub.service;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.videoclub.domain.Idioma;
import org.iesvdm.videoclub.exception.CategoriaNotFoundException;
import org.iesvdm.videoclub.repository.IdiomaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class IdiomaService {
    IdiomaRepository idiomaRepository;

    public IdiomaService(IdiomaRepository idiomaRepository) {
        this.idiomaRepository = idiomaRepository;
    }

    public List<Idioma> all() {
        return this.idiomaRepository.findAll();
    }

    public Idioma save(Idioma idioma) {
        return this.idiomaRepository.save(idioma);
    }

    public Idioma one(Long id) {
        return this.idiomaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNotFoundException(id));
    }

    public Idioma replace(Long id, Idioma idioma) {
        return this.idiomaRepository.findById(id).map(p -> (id.equals(idioma.getId()) ?
                        this.idiomaRepository.save(idioma) : null))
                .orElseThrow(() -> new CategoriaNotFoundException(id));
    }

    public void delete(Long id) {
        this.idiomaRepository.findById(id).map(p -> {
                    this.idiomaRepository.delete(p);
                    return p;
                })
                .orElseThrow(() -> new CategoriaNotFoundException(id));
    }

}
