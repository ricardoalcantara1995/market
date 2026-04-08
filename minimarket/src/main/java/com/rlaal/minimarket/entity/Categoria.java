package com.rlaal.minimarket.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;


@Entity
@Table(name = "categoria")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "categoria_id")
    private UUID id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "fecha_creacion")
    private Date fechaCreacion;
    @Column(name = "fecha_actualizacion")
    private Date fechaActualizacion;
}
