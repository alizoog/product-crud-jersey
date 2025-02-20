package com.example.productcrud.service;

import com.example.productcrud.common.PageableRequest;
import com.example.productcrud.common.ProductStatus;
import com.example.productcrud.dao.ProductDAO;
import com.example.productcrud.dto.ProductPayload;
import com.example.productcrud.model.Product;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ProductService {

    private final ProductDAO productDAO = new ProductDAO();

    public Product getProductById(Long id) {
        return productDAO.getById(id);
    }

    public List<Product> getList(String search, ProductStatus status, PageableRequest pageable) {
        if (search == null || search.isEmpty()) search = "";
        return productDAO.searchByNameAndStatus(search, getStatusList(status), pageable.getPage(), pageable.getSize());
    }


    public Product save(Product product, ProductPayload payload) {
        product.setName(payload.getName());
        product.setPrice(payload.getPrice());
        product.setQuantity(payload.getQuantity());
        product.setStatus(payload.getStatus());
        return productDAO.save(product);
    }

    public Product create(ProductPayload payload) {
        Product product = new Product();
        return save(product, payload);
    }

    public Product update(Long id, ProductPayload payload) {
        Product product = getProductById(id);
        return save(product, payload);
    }

    public void delete(Long id) {
        productDAO.deleteById(id);
    }

    public long countProducts(String search, ProductStatus status) {
        return productDAO.count(search, getStatusList(status));
    }

    List<ProductStatus> getStatusList(ProductStatus status) {
        List<ProductStatus> statusList = new ArrayList<>();
        if (status == null || status == ProductStatus.DELETED) {
            statusList.add(ProductStatus.ACTIVE);
            statusList.add(ProductStatus.INACTIVE);
        } else {
            statusList.add(status);
        }
        return statusList;
    }
}
