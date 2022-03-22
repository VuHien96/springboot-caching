package com.example.demo.controller;

import com.example.demo.locale.Translator;
import com.example.demo.model.api.APIResponse;
import com.example.demo.model.api.Status;
import com.example.demo.model.dto.PageMetaData;
import com.example.demo.model.dto.ProductDTO;
import com.example.demo.service.ProductService;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by VuHien96 on 25/05/2020 - 8:07 PM
 */
@RestController
@RequestMapping("/products")
@Slf4j
@Builder
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/pro")
    public ResponseEntity<Object> getListProducts(
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @RequestParam(defaultValue = "2", required = false) Integer size,
            @RequestParam(defaultValue = "id", required = false) String sortBy,
            @RequestParam(defaultValue = "", required = false) String searchKey) {

        log.info("******************** Start Get list Produc ***************");

        Page<ProductDTO> productDTOS = productService.getListProducts(page, size, sortBy,searchKey);

        PageMetaData pageMetaData = new PageMetaData();
        pageMetaData.setSize(productDTOS.getSize());
        pageMetaData.setTotalElement(productDTOS.getTotalElements());
        pageMetaData.setTotalPage(productDTOS.getTotalPages());

        Status status = Status.builder()
                .code(HttpStatus.OK.value())
                .message(Translator.toLocale("msg.record.success"))
                .description(Translator.toLocale("msg.record.description"))
                .build();
        APIResponse<Object> response = APIResponse.builder()
                .status(status)
                .data(productDTOS.getContent())
                .message(Translator.toLocale("msg.record.description"))
                .pageMetadata(pageMetaData)
                .build();
        log.info("******************** End Get list Produc ***************");

        return ResponseEntity.ok(response);

    }

    @GetMapping
    public ResponseEntity<APIResponse<List<ProductDTO>>> getListProduct() {
        log.info("get list product");
        Long start = System.currentTimeMillis();
        APIResponse<List<ProductDTO>> response = new APIResponse<>();
        List<ProductDTO> products = productService.getListProduct();
        response.setStatus(HttpStatus.OK.value());
        response.setData(products);
        response.setMessage("get list product success!");
        Long end = System.currentTimeMillis();
        System.out.println("time: " + (end - start));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable int id) {
        log.info("get product by id");
        ProductDTO productDTO = productService.getProductById(id);
        return ResponseEntity.ok(productDTO);
    }

    @PostMapping
    public ResponseEntity<Object> createProduct(@Valid @RequestBody ProductDTO productDTO) {
        log.info("create product");
        productService.createProduct(productDTO);
        Status status = Status.builder()
                .code(HttpStatus.OK.value())
                .message(Translator.toLocale("msg.record.success"))
                .description(Translator.toLocale("msg.record.description"))
                .build();
        APIResponse<Object> response = APIResponse.builder()
                .status(status)
                .message(Translator.toLocale("msg.record.success"))
                .data(productDTO)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@RequestBody ProductDTO productDTO, @PathVariable int id) {
        log.info("update product");
        productService.updateProduct(productDTO, id);
        Status status = Status.builder()
                .code(HttpStatus.OK.value())
                .message(Translator.toLocale("msg.record.success"))
                .description(Translator.toLocale("msg.record.description"))
                .build();
        APIResponse<Object> response = APIResponse.builder()
                .status(status)
                .data(productDTO)
                .message(Translator.toLocale("msg.record.description"))
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable int id) {
        log.info("delete product");
        productService.deleteProduct(id);
        return ResponseEntity.ok("Delete success!");
    }

}
