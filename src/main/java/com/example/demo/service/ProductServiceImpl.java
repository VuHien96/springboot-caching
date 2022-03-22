package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.locale.Translator;
import com.example.demo.model.dto.ProductDTO;
import com.example.demo.model.mapper.ProductMapper;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by VuHien96 on 25/05/2020 - 7:59 PM
 */
@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<ProductDTO> getListProducts(Integer page, Integer size, String sortBy, String searchKey) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, sortBy));
        Page<Product> productPage = productRepository.getListProduct(pageable,searchKey);
        return productPage.map(ProductMapper::productDTOToProduct);

    }

    @Override
    @Cacheable("products")
    public List<ProductDTO> getListProduct() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOS = new ArrayList<>();
        for (Product product : products) {
            productDTOS.add(ProductMapper.productDTOToProduct(product));
        }
        return productDTOS;
    }

    @Override
    @Cacheable("product")
    public ProductDTO getProductById(int id) {
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent()) {
            throw new RecordNotFoundException(Translator.toLocale("error.msg.record.not_found"));
        }
        return ProductMapper.productDTOToProduct(product.get());
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "products", allEntries = true),
            @CacheEvict(value = "product", allEntries = true)})
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = ProductMapper.toProduct(productDTO);
        productRepository.save(product);
        return ProductMapper.productDTOToProduct(product);
    }

    @Override
    @CachePut(value = "product",key = "{#result.id}")
    public ProductDTO updateProduct(ProductDTO productDTO, int id) {
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent()) {
            throw new RecordNotFoundException(Translator.toLocale("error.msg.record.not_found"));
        }
        Product updateProduct = ProductMapper.toProduct(productDTO, id);
        productRepository.save(updateProduct);
        return ProductMapper.productDTOToProduct(updateProduct);

    }

    @Override

    public void deleteProduct(int id) {
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent()) {
            throw new RecordNotFoundException(Translator.toLocale("error.msg.record.not_found"));
        }
        productRepository.deleteById(id);
    }

}
