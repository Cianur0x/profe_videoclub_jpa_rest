package org.iesvdm.videoclub.repository;

import org.iesvdm.videoclub.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    // Métodos creados por JPA
    List<Categoria> findCategoriaByNombreContainingIgnoreCase(String nombre);

    List<Categoria> findCategoriaByNombreContainingIgnoreCaseOrderByNombreAsc(String nombre);

    List<Categoria> findCategoriaByNombreContainingIgnoreCaseOrderByNombreDesc(String nombre);

    List<Categoria> findAllByOrderByNombreAsc();

    List<Categoria> findAllByOrderByNombreDesc();
}
