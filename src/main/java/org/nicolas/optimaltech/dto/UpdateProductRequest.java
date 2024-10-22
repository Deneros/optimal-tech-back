package org.nicolas.optimaltech.dto;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class UpdateProductRequest {

    private String name;

    @Min(value = 0, message = "El precio debe ser mayor o igual a 0")
    private Double price;

    @Min(value = 0, message = "El stock debe ser mayor o igual a 0")
    private Integer stock;

    @Min(value = 0, message = "El stock mínimo debe ser mayor o igual a 0")
    private Integer stockMinimum;
}
