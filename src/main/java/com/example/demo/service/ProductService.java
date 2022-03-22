package com.example.demo.service;

import com.example.demo.model.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by VuHien96 on 25/05/2020 - 7:46 PM
 */
@Service
public interface ProductService {
    Page<ProductDTO> getListProducts(Integer page,Integer size,String sortBy,String searchKey);
    List<ProductDTO> getListProduct();
    ProductDTO getProductById(int id);
    ProductDTO createProduct(ProductDTO productDTO);
    ProductDTO updateProduct(ProductDTO productDTO,int id);
    void deleteProduct(int id);
}
