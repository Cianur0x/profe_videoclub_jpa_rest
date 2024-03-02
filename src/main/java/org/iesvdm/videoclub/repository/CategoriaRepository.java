package org.iesvdm.videoclub.repository;

import org.iesvdm.videoclub.domain.Categoria;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    // Métodos creados por JPA
    List<Categoria> findCategoriaByNombreContainingIgnoreCase(String nombre);

    List<Categoria> findCategoriaByNombreContainingIgnoreCase(String nombre, Sort sort);

    List<Categoria> findAllByOrderByNombreAsc();

    List<Categoria> findAllByOrderByNombreDesc();
}
