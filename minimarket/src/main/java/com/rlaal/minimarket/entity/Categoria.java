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
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "fecha_creacion",updatable = false, insertable = false)
    private LocalDateTime fechaCreacion;
    @Column(name = "fecha_actualizacion",insertable = false)
    private LocalDateTime  fechaActualizacion;
    @Column(name = "activo", nullable = false)
    private boolean activo = true;

    public Categoria(UUID id, boolean activo, LocalDateTime fechaCreacion, String descripcion, String nombre, LocalDateTime fechaActualizacion) {
        this.id = id;
        this.activo = activo;
        this.fechaCreacion = fechaCreacion;
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.fechaActualizacion = fechaActualizacion;
    }


    public Categoria(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
