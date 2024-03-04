package org.iesvdm.videoclub.repository;

import org.iesvdm.videoclub.domain.Categoria;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    // Métodos creados por JPA
    List<Categoria> findCategoriaByNombreContainingIgnoreCase(String nombre);

    List<Categoria> findCategoriaByNombreContainingIgnoreCase(String nombre, Sort sort);

    List<Categoria> findAllByOrderByNombreAsc();

    List<Categoria> findAllByOrderByNombreDesc();
}
