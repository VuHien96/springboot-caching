package com.example.demo.repository;


import com.example.demo.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by VuHien96 on 25/05/2020 - 7:46 PM
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("FROM Product p " +
            "WHERE p.name like %:searchKey% " +
            "OR p.description like %:searchKey%")
    Page<Product> getListProduct(Pageable pageable, @Param("searchKey") String searchKey);
}
