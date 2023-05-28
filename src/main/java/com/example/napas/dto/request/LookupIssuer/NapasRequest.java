package com.example.napas.dto.request.LookupIssuer;

import com.example.napas.dto.Header.HeaderNapas;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Data
public class NapasRequest {
    @Valid
    private HeaderNapas headerNapas;

    @Valid
    private Payload payload;

    @Data
    public static class Payload {
//        @NotBlank
        private String payment_reference;

//        @NotBlank
        private String qr_string;
    }
}
