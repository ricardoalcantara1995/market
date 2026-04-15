package com.rlaal.minimarket.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@Getter
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductoResponseDTO {
    private UUID id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private Integer stock;
    private String nombreCategoria;
    private Boolean activo;

    public ProductoResponseDTO(UUID id, String nombreCategoria, Integer stock, BigDecimal precio, String descripcion, String nombre) {
        this.id = id;
        this.stock = stock;
        this.precio = precio;
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.nombreCategoria = nombreCategoria;
        this.activo = null;
    }
}
