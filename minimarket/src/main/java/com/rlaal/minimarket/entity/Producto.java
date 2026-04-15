package com.rlaal.minimarket.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "producto")

public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "producto_id")
    @EqualsAndHashCode.Include
    private UUID id;
    @Column(name = "nombre",unique = true,nullable = false)
    private String nombre;
    @Column(name = "descripcion",nullable = false)
    private String descripcion;
    @Column(name = "precio")
    private BigDecimal precio;
    @Column(name = "stock",nullable = false)
    private Integer stock;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id",nullable = false)
    private Categoria categoria;
    @Column(name = "fecha_creacion",updatable = false, insertable = false)
    private LocalDateTime fechaCreacion;
    @Column(name = "fecha_actualizacion",insertable = false)
    private  LocalDateTime fechaActualizacion;
    @Column(name = "activo", nullable = false)
    private boolean activo =true;

    public Producto(UUID id, boolean activo, LocalDateTime fecha_actualizacion, Categoria categoria, BigDecimal precio, String descripcion, String nombre, Integer stock, LocalDateTime fecha_creacion) {
        this.id = id;
        this.activo = activo;
        this.fechaActualizacion = fecha_actualizacion;
        this.categoria = categoria;
        this.precio = precio;
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.stock = stock;
        this.fechaCreacion = fecha_creacion;
    }

    public Producto(UUID id,String nombre, String descripcion, BigDecimal precio, Integer stock, Categoria categoria) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
    }

    public Producto() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
