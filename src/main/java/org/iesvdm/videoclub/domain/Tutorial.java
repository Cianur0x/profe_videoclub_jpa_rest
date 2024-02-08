package org.iesvdm.videoclub.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "tutorial", schema = "videoclub_jpa", indexes = {@Index(columnList = "titulo")})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
// puedo añadir, pero no puedo modificar
public class Tutorial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;

    @Column(name = "titulo", length = 50)
    private String titulo;

    @Column(name = "descripcion", length = 50)
    private String descripcion;

    @Column(name = "publicado", length = 50)
    private boolean publicado;

    @Column(nullable = false)
    private Date fechaPublicacion;

    // lazy es que tiene un proxi intermedio que cuando realemnte se vayan a cargar los datos apunta a eso, y hats que no lo usas no se trae los datos
    @OneToMany(mappedBy = "tutorial", fetch = FetchType.LAZY, cascade = CascadeType.ALL) // por defecto fetch es lazy
    // @OneToMany(mappedBy = "tutorial", cascade = CascadeType.ALL)
    // si ponemos EAGER tarda un poco más
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Comentario> comentarios;

    public Tutorial addComentario(Comentario comentario) {
        comentario.setTutorial(this);
        this.comentarios.add(comentario);

        // es un herlper sin en el helper habra que setearlo cuando creamos un comentario
        return this;
    }

    public Tutorial removeComentario(Comentario comentario) {
        this.comentarios.remove(comentario);
        comentario.setTutorial(null); // si se elimina de la coleccion se queda huerfano
        // asi que setamos el tutorial al null para qeu no tenga más referencias
        return this;
    }
}
