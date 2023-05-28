package com.example.napas.controller;

import com.example.napas.dto.request.LookupIssuer.NapasRequest;
import com.example.napas.dto.response.LookupIssuer.NapasResponse;
import com.example.napas.service.LookupIssuerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping(value = "/infogw/qr/v1")
public class LookupIssuerController {
    @Autowired
    LookupIssuerService lookupIssuerService;

    @PostMapping(value =  "/issuer/lookup")
    public NapasResponse ResponseNapas(@RequestBody NapasRequest napasRequest) throws UnsupportedEncodingException {
        return lookupIssuerService.parseQRString(napasRequest);
    }

}
