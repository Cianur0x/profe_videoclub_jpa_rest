package org.iesvdm.videoclub.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "socio")
public class Socio {
    @Id
    private String dni;

    @Column(name = "nombre", length = 50)
    private String nombre;

    @Column(name = "apellido", length = 50)
    private String apellido;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tarjeta_id", foreignKey = @ForeignKey(name = "FK_TARJETA"))
    private Tarjeta tarjeta;
}
