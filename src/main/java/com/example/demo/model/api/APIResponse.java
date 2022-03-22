package com.example.demo.model.api;

import com.example.demo.model.dto.PageMetaData;
import lombok.*;

/**
 * Created by VuHien96 on 26/05/2020 - 1:55 PM
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class APIResponse<T> {
    private Object status;
    private String message;
    private Object data;
    private PageMetaData pageMetadata;
}
