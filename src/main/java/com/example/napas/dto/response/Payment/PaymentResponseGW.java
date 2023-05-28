package com.example.napas.dto.response.Payment;

import com.example.napas.dto.Header.HeaderGW;
import lombok.Data;

@Data
public class PaymentResponseGW {
    private HeaderGW header;

    private Data data;

    @lombok.Data
    public static class Data {

        private String responseCode;

        private String responseDesc;

        private Payment payment;

        private String amount;

        private String currency;

        private String settlementAmount;

        private String settlementCurency;

        private String settlementDate;


    }

    @lombok.Data
    public static class Payment{


        private String trace;

        private String exchangeRate;

        private String payRefNo;

        private String authorizationCode;

        private String reference;

    }


}

