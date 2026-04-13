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
    private  Boolean  activo;

    public CategoriaResponseDTO(UUID id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.activo =null;
    }


}
