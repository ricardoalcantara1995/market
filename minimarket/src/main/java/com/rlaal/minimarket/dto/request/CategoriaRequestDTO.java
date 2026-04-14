package com.rlaal.minimarket.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CategoriaRequestDTO {
    @NotBlank(message = "Se requiere el nombre de la categoria")
    private String nombre;
    @NotBlank(message = "Se requiere la descripcion de la categoria")
    private String descripcion;
}
