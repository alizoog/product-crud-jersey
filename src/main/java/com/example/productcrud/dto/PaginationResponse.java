package com.example.productcrud.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaginationResponse<T> {
    private List<T> data;
    private Metadata metadata;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Metadata {
        private int currentPage;
        private int totalPages;
        private long totalItems;
        private int pageSize;
    }
}