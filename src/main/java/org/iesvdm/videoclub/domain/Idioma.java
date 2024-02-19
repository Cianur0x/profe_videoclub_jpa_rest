package org.iesvdm.videoclub.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "idioma")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//Si utilizo @OneToMany(FetchType.LAZY) además debo usar
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Idioma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_idioma")
    private Long id;

    private String nombre;

    @Column(name = "ultima_actualizacion")
    @JsonFormat(pattern = "yyyy-MM-dd-HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDate ultimaActualizacion;

    @OneToMany(mappedBy = "idioma", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Pelicula> peliculasIdioma;

//    @OneToMany(mappedBy = "idiomaOriginal")
//    @JsonIgnore
//    private HashSet<Pelicula> peliculasIdiomaOriginal;
}
