package org.iesvdm.videoclub.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.OnDelete;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pelicula")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pelicula")
    private long idPelicula;

    private String titulo;

    private String descripcion;

//    @Column(name = "anyo_lanzamiento")
//    @JsonFormat(pattern = "yyyy", shape = JsonFormat.Shape.STRING)
//    private Date anyoLanzamiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(name = "id_idioma")
    private Idioma idioma;

    @Transient // para guardar datos adicionals y que no se graben en la base de datos
    private Long idIdioma;

//    @ManyToOne
//    @JoinColumn(name = "id_idioma_original")
//    private Idioma idiomaOriginal;

//    @Column(name = "duracion_alquiler")
//    private int duracionAlquiler;
//
//    @Column(name = "rental_rate")
//    private BigDecimal rentalRate;
//    private int duracion;
//
//    @Column(name = "replacement_cost")
//    private BigDecimal replacementCost;
//
//    private String clasificacion;
//
//    @Column(name = "caracteristicas_especiales")
//    private String caracteristicasEspeciales;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "pelicula_categoria", joinColumns = @JoinColumn(name = "id_pelicula", referencedColumnName = "id_pelicula"),
            inverseJoinColumns = @JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria"))
    Set<Categoria> categorias = new HashSet<>();

    @Column(name = "ultima_actualizacion")
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private Date ultimaActualizacion;

}
