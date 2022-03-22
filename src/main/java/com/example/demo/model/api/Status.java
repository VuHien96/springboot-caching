package com.example.demo.model.api;

import lombok.*;

/**
 * Created by VuHien96 on 27/05/2020 - 2:24 PM
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Status {
    private int code;
    private String message;
    private String description;
}
