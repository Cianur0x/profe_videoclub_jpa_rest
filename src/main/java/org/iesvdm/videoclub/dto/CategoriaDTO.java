package org.iesvdm.videoclub.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.iesvdm.videoclub.domain.Categoria;

@EqualsAndHashCode(callSuper = true)
@Data
public class CategoriaDTO extends Categoria {

    private int peliculasAsociadas;

    public CategoriaDTO(Categoria categoria, int conteo) {
        super(categoria.getId(), categoria.getNombre(), categoria.getPeliculas(), categoria.getUltimaActualizacion());
        this.peliculasAsociadas = conteo;
    }

}
