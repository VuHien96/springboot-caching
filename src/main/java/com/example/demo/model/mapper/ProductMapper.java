package com.example.demo.model.mapper;

import com.example.demo.entity.Product;
import com.example.demo.model.dto.ProductDTO;

/**
 * Created by VuHien96 on 26/05/2020 - 10:46 AM
 */
public class ProductMapper {
    public static ProductDTO productDTOToProduct(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        return productDTO;
    }

    public static Product toProduct(ProductDTO productDTO){
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        return product;
    }

    public static Product toProduct(ProductDTO productDTO,int id){
        Product product = new Product();
        product.setId(id);
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        return product;
    }

}
