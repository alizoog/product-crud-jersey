package com.example.productcrud.dto;

import com.example.productcrud.common.ProductStatus;
import com.example.productcrud.model.Product;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ProductResponse {
    private Long id;
    private String name;
    private Double price;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Tashkent")
    private Date createdAt;
    private ProductStatus status;
    private Integer quantity;

    public ProductResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.createdAt = product.getCreatedAt();
        this.status = product.getStatus();
        this.quantity = product.getQuantity();
    }
}
