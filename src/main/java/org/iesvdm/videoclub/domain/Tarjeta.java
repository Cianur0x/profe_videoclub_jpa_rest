package org.iesvdm.videoclub.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tarjeta")
public class Tarjeta {
    @Id
    private long numero;

    @Column(nullable = false)
    private Date fechaCaducidad;

    @OneToOne(mappedBy = "tarjeta")
    private Socio socio;
}
