package com.example.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * Created by VuHien96 on 26/05/2020 - 10:44 AM
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ProductDTO {

    private int id;

    @NotBlank(message = "{error.msg.record.valid}")
    private String name;

    @NotBlank(message = "{error.msg.record.valid}")
    private String description;


    private float price;
}
