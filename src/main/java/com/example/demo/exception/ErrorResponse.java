package com.example.demo.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by VuHien96 on 26/05/2020 - 10:31 AM
 */
@XmlRootElement(name = "error")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ErrorResponse {

    private int code;
    //General message about error
    private String message;
    //errors in API request processing
    private List<String> details;


}
