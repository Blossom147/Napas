package com.example.napas.controller;

import com.example.napas.dto.request.LookupIssuer.LookupIssuerRequestGW;
import com.example.napas.dto.request.LookupIssuer.NapasRequest;
import com.example.napas.dto.request.Payment.PaymentRequestGW;
import com.example.napas.dto.response.LookupIssuer.LookupIssuerResponseGW;
import com.example.napas.dto.response.LookupIssuer.NapasResponse;
import com.example.napas.dto.response.Payment.PaymentResponseGW;
import com.example.napas.dto.response.Payment.PaymentResponseNapas;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping(value = "/infogw/qr/v1")
public class IssuerBankController {

    @PostMapping(value =  "/issuerbankPayment")
    public PaymentResponseGW ResponseGW(@RequestBody PaymentRequestGW paymentRequestGW) {
        PaymentResponseGW paymentResponseGW = new PaymentResponseGW();
        return paymentResponseGW;
    }
    @PostMapping(value =  "/issuerbankLookup")
    public LookupIssuerResponseGW ResponseGW(@RequestBody LookupIssuerRequestGW lookupIssuerRequestGW) {
        LookupIssuerResponseGW lookupIssuerResponseGW = new LookupIssuerResponseGW();
        return lookupIssuerResponseGW;
    }


}
