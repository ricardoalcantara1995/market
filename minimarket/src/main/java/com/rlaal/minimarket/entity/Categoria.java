package com.rlaal.minimarket.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;


@Entity
@Table(name = "categoria")
 public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "categoria_id")
    private UUID id;
    @Column(name = "nombre",unique = true,nullable = false)
    private String nombre;
    @Column(name = "fecha_creacion",updatable = false, insertable = false)
    private LocalDateTime fechaCreacion;
    @Column(name = "fecha_actualizacion",insertable = false)
    private LocalDateTime  fechaActualizacion;

    public Categoria(UUID id, LocalDateTime fechaActualizacion, LocalDateTime fechaCreacion, String nombre) {
        this.id = id;
        this.fechaActualizacion = fechaActualizacion;
        this.fechaCreacion = fechaCreacion;
        this.nombre = nombre;
    }
    public Categoria(String nombre) {
        this.nombre = nombre;
    }

    public Categoria() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
