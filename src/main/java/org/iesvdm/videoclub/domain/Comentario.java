package org.iesvdm.videoclub.domain;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;

    @Column(name = "texto_comentario", length = 50)
    private String texto;

    @ManyToOne
    @JoinColumn(name = "tutorial_id", nullable = false, foreignKey = @ForeignKey(name = "FK_tutorial"))
    @ToString.Exclude // se bloquea uno de los lados, pq es como un bucle
    private Tutorial tutorial;
}
