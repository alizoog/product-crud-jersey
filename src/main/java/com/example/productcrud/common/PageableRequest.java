package com.example.productcrud.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageableRequest {
    private int page = 1;
    private int size = 10;
}
