package com.example.productcrud.dao;

import com.example.productcrud.common.ProductStatus;
import com.example.productcrud.exception.ResourceNotFoundException;
import com.example.productcrud.model.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class ProductDAO {
    private final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("examplePU");

    public List<Product> searchByNameAndStatus(String name, List<ProductStatus> statusList, int page, int size) {
        EntityManager em = emf.createEntityManager();
        List<Product> res = em.createQuery(
                        """
                                SELECT p
                                FROM Product p
                                WHERE LOWER(p.name) LIKE LOWER(:name)
                                    AND p.status IN (:status)
                                ORDER BY p.createdAt DESC
                                """,
                        Product.class)
                .setParameter("name", "%" + name + "%")
                .setParameter("status", statusList)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
        em.close();
        return res;
    }

    public Product getById(Long id) {
        EntityManager em = emf.createEntityManager();
        Product product = em.find(Product.class, id);
        if (product == null || product.getStatus() == ProductStatus.DELETED)
            throw new ResourceNotFoundException("Product with id " + id + " not found");
        em.close();
        return product;
    }

    public Product save(Product product) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        if (product.getId() == null)
            em.persist(product);
        else em.merge(product);
        em.getTransaction().commit();
        em.close();
        return product;
    }

    public Long count(String search, List<ProductStatus> statusList) {
        EntityManager em = emf.createEntityManager();
        Long res = em.createQuery("SELECT COUNT(p) FROM Product p WHERE LOWER(p.name) LIKE LOWER(:search) AND p.status IN (:status)", Long.class)
                .setParameter("search", "%" + search + "%")
                .setParameter("status", statusList)
                .getSingleResult();
        em.close();
        return res;
    }

    public void deleteById(Long productId) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Product product = em.find(Product.class, productId);
        if (product != null) {
            if (product.getStatus() == ProductStatus.DELETED)
                throw new ResourceNotFoundException("Product with id " + productId + " not found");
            product.setStatus(ProductStatus.DELETED);
            em.merge(product);
        }
        em.getTransaction().commit();
        em.close();
        if (product == null)
            throw new ResourceNotFoundException("Product not found");
    }
}
