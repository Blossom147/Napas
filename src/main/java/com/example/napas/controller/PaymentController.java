package com.example.napas.controller;

import com.example.napas.dto.request.LookupIssuer.NapasRequest;
import com.example.napas.dto.request.Payment.PaymentRequestNapas;
import com.example.napas.dto.response.LookupIssuer.NapasResponse;
import com.example.napas.dto.response.Payment.PaymentResponseNapas;
import com.example.napas.service.LookupIssuerService;
import com.example.napas.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping(value = "/infogw/qr/v1")
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    @PostMapping(value =  "/issuer/payment")
    public PaymentResponseNapas ResponseNapas(@RequestBody PaymentRequestNapas paymentRequestNapas) throws UnsupportedEncodingException {
        return paymentService.genResNapas(paymentRequestNapas);
    }
}
