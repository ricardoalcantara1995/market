package com.rlaal.minimarket.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import java.util.UUID;
@AllArgsConstructor
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
 public class CategoriaResponseDTO {
    private UUID id;
    private String nombre;
    private  String descripcion;
    private  Boolean  activo;

    public CategoriaResponseDTO( UUID id,String nombre, String descripcion) {
        this.nombre = nombre;
        this.id = id;
        this.descripcion = descripcion;
        this.activo = null;
    }
}
