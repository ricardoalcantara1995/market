package com.rlaal.minimarket.dto.response;

import lombok.*;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CategoriaResponseDTO {
    private UUID id;
    private String nombre;
}
