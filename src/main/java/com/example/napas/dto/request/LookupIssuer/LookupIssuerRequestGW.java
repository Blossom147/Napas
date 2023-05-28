package com.example.napas.dto.request.LookupIssuer;

import com.example.napas.dto.Header.HeaderGW;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;


@Data
public class LookupIssuerRequestGW {

        @Valid
        private HeaderGW headerGW;

        @Valid
        private Data data;


        @lombok.Data
        public static class Data {

            @NotBlank
            private String qrString;

            @NotBlank
            private String channel;
        }
}
