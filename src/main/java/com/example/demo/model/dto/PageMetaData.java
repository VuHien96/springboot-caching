package com.example.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by VuHien96 on 26/05/2020 - 1:56 PM
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PageMetaData {
    private int size;
    private long totalElement;
    private int totalPage;
    private int currentPage;
    private int nextPageURL;
    private int  previousPageURL;
}
