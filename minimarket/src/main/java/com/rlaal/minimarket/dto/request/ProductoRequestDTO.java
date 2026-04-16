package com.rlaal.minimarket.dto.request;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductoRequestDTO {
    @NotBlank(message = "Se require nombre para crear producto")
    private String nombre;
    @NotBlank(message = "Debe colocar una descripción")
    private String descripcion;
    @NotNull(message = "Debe colocar precio")
    @DecimalMin(value = "0.0", message = "El precio no puede ser negativo")
    private BigDecimal precio;
    @NotNull(message = "Debe colocar stock valido")
    @Min( value = 0, message = "El stock nunca puede ser negativo")
    private Integer stock;
    @NotNull(message = "Todo producto debe tener una categoria")
    private UUID categoriaId;
}
