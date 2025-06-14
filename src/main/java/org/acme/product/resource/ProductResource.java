package org.acme.product.resource;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.product.entity.Product;
import org.acme.product.service.ProductService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import java.util.List;

@Path("/api/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Product Resource", description = "Product management endpoints")
public class ProductResource {

    @Inject
    ProductService productService;

    @POST
    @Operation(summary = "Create a new product")
    public Response createProduct(@Valid Product product) {
        Product created = productService.createProduct(product);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @GET
    @Operation(summary = "Get all products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GET
    @Path("/sorted-by-price")
    @Operation(summary = "Get all products sorted by price in ascending order")
    public List<Product> getAllProductsSortedByPrice() {
        return productService.getAllProductsByPriceAsc();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Get a product by ID")
    public Response getProduct(@PathParam("id") Long id) {
        Product product = productService.getProduct(id);
        return product != null ? Response.ok(product).build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Update a product")
    public Response updateProduct(@PathParam("id") Long id, @Valid Product product) {
        Product updated = productService.updateProduct(id, product);
        return updated != null ? Response.ok(updated).build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Delete a product")
    public Response deleteProduct(@PathParam("id") Long id) {
        boolean deleted = productService.deleteProduct(id);
        return deleted ? Response.noContent().build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/{id}/check-stock/{count}")
    @Operation(summary = "Check if product stock is available")
    public Response checkStockAvailability(@PathParam("id") Long id, @PathParam("count") Integer count) {
        boolean available = productService.checkStockAvailability(id, count);
        return Response.ok(new StockAvailabilityResponse(available)).build();
    }

    // Inner class for stock availability response
    public static class StockAvailabilityResponse {
        public boolean available;

        public StockAvailabilityResponse(boolean available) {
            this.available = available;
        }
    }
}