package com.example.productcrud.resource;

import com.example.productcrud.common.PageableRequest;
import com.example.productcrud.common.ProductStatus;
import com.example.productcrud.dto.PaginationResponse;
import com.example.productcrud.dto.ProductPayload;
import com.example.productcrud.dto.ProductResponse;
import com.example.productcrud.service.ProductService;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {
    private final ProductService productService = new ProductService();

    @POST
    @Path("/list")
    public Response getAll(@QueryParam("search") String search, @QueryParam("status") ProductStatus status, PageableRequest pr) {
        if (pr == null) pr = new PageableRequest();

        List<ProductResponse> responses = productService.getList(search, status, pr)
                .stream().map(ProductResponse::new)
                .collect(Collectors.toList());

        long totalItems = productService.countProducts(search == null ? "" : search, status);
        int totalPages = (int) Math.ceil((double) totalItems / pr.getSize());

        PaginationResponse<ProductResponse> response = new PaginationResponse<>(responses, new PaginationResponse.Metadata(pr.getPage(), totalPages, totalItems, pr.getSize()));

        return Response.ok(response).build();
    }

    @POST
    public Response addProduct(ProductPayload payload) {
        return Response.ok(new ProductResponse(productService.create(payload))).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateProduct(@PathParam("id") Long id, @Valid ProductPayload payload) {
        return Response.ok(new ProductResponse(productService.update(id, payload))).build();
    }

    @DELETE
    @Path("/{id}")
    public String deleteProduct(@PathParam(value = "id") Long id) {
        productService.delete(id);
        return "Product deleted";
    }
}
