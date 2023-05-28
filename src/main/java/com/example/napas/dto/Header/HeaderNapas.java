package com.example.napas.dto.Header;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Data
public class HeaderNapas {
    @Valid
    Requestor requestor;

//    @NotBlank
    private String reference_id;

    private Number timestamp;

//    @NotBlank
    private String operation;

    private String signature;


    @Data
    public static class Requestor  {

//        @NotBlank
        private String id;


//        @NotBlank
        private String name;
    }
}
