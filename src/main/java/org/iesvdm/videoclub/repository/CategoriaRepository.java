package org.iesvdm.videoclub.repository;

import org.iesvdm.videoclub.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List<Categoria> findCategoriaByNombreContainingIgnoreCase(String nombre);
    List<Categoria> findCategoriaByNombreContainingIgnoreCaseOrderByNombreAsc(String nombre);
    List<Categoria> findCategoriaByNombreContainingIgnoreCaseOrderByNombreDesc(String nombre);
    List<Categoria> findAllByOrderByNombreAsc();
    List<Categoria> findAllByOrderByNombreDesc();
}
