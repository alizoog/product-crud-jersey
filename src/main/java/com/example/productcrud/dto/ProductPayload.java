package com.example.productcrud.dto;

import com.example.productcrud.common.ProductStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductPayload {
    @NotNull
    private String name;
    @NotNull
    private Double price;
    @NotNull
    private Integer quantity = 0;
    @NotNull
    private ProductStatus status = ProductStatus.ACTIVE;
}
