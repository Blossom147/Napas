package com.example.napas.service;

import com.example.napas.constant.CommonConstant;
import com.example.napas.constant.ErrorDefination;
import com.example.napas.constant.QRCodeFormat;
import com.example.napas.dto.Header.HeaderNapas;
import com.example.napas.dto.request.LookupIssuer.NapasRequest;
import com.example.napas.dto.response.LookupIssuer.NapasResponse;
import com.example.napas.exception.ValidationHelper;
import com.example.napas.repo.BankRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;

@Service
public class LookupIssuerService {

    @Autowired
    BankRepo bankRepo;

    private void putHashMapAndCutQrString(String string, LinkedHashMap<String, String> linkedHashMap, String qrString) {
        while (!qrString.isEmpty()) {
            linkedHashMap.put(string + qrString.substring(0, 2), qrString.substring(4, 4 + Integer.parseInt(qrString.substring(2, 4))));
            qrString = qrString.replace(qrString.substring(0, 4) + qrString.substring(4, 4 + Integer.parseInt(qrString.substring(2, 4))), "");
        }
    }

    public NapasResponse parseQRString(NapasRequest napasRequest) throws UnsupportedEncodingException {
        NapasResponse napasResponse = new NapasResponse();

        HeaderNapas headerNapas = new HeaderNapas();
        napasResponse.setHeaderNapas(headerNapas);
//      Xét Header
//       Xét Operation Header
        headerNapas.setOperation(CommonConstant.RES_GB);

        NapasResponse.Result result = new NapasResponse.Result();
        napasResponse.setResult(result);
//      Xét Requestor Header
        HeaderNapas.Requestor requestor = new HeaderNapas.Requestor();
        requestor.setId("1");
        requestor.setName("Nguyen Xuan Long");
        headerNapas.setRequestor(requestor);

        if (!ValidationHelper.isValid(napasRequest)) {
//          Xét result Response
            result.setCode(ErrorDefination.ERR_004.getErrCode());
            result.setDescription(ErrorDefination.ERR_004.getDesc());
        }
//        else if (!checkCRC(napasRequest.getPayload().getQr_string().trim())) {
//            System.out.println(checkCRC(napasRequest.getPayload().getQr_string().trim()));
//            napasResponse.getResult().setCode(ErrorDefination.ERR_008.getErrCode());
//            napasResponse.getResult().setDescription(ErrorDefination.ERR_008.getDesc());
//        }
        else {
            String qrString = napasRequest.getPayload().getQr_string().trim();

            LinkedHashMap<String, String> linkedHashMapQRString = new LinkedHashMap<>();

            putHashMapAndCutQrString("", linkedHashMapQRString, qrString);

            String valueOfID38 = linkedHashMapQRString.get(QRCodeFormat.CONSUMER_ACCOUNT_INFO.getId());
            putHashMapAndCutQrString(QRCodeFormat.CONSUMER_ACCOUNT_INFO.getId() + ".", linkedHashMapQRString, valueOfID38);

            String valueOfID38_01 = linkedHashMapQRString.get(QRCodeFormat.CONSUMER_ACCOUNT_INFO.getId() + "." + QRCodeFormat.MEMBER_BANKS.getId());
            putHashMapAndCutQrString(QRCodeFormat.CONSUMER_ACCOUNT_INFO.getId() + "." + QRCodeFormat.MEMBER_BANKS.getId() + ".", linkedHashMapQRString, valueOfID38_01);

            String valueOfID62 = linkedHashMapQRString.get(QRCodeFormat.ADDITION_INFO.getId());

            napasResponse.getResult().setId("1");
            napasResponse.getResult().setCode(ErrorDefination.ERR_OOO.getErrCode());
            napasResponse.getResult().setDescription(ErrorDefination.ERR_OOO.getDesc());

            NapasResponse.Payload payload = new NapasResponse.Payload();
            NapasResponse.Payload.Payment payment = new NapasResponse.Payload.Payment();
            NapasResponse.Payload.Recipient recipient = new NapasResponse.Payload.Recipient();
            NapasResponse.Payload.Participant participant = new NapasResponse.Payload.Participant();
            NapasResponse.Order_info order_info = new NapasResponse.Order_info();
            NapasResponse.Additional_info additional_info = new NapasResponse.Additional_info();

            NapasResponse.Payload.Recipient.Address address = new NapasResponse.Payload.Recipient.Address();

            payment.setType("QR_PUSH");
            payment.setPayment_reference(CommonConstant.REFERENCE_NUMBER);
            payload.setAmount(linkedHashMapQRString.get(QRCodeFormat.TRANSACTION_AMOUNT.getId()));
            payload.setCurrency(linkedHashMapQRString.get(QRCodeFormat.TRANSACTION_CURRENCY.getId()));

            payload.setParticipant(participant);

            payload.getParticipant().setOriginating_institution_id(linkedHashMapQRString.get(QRCodeFormat.CONSUMER_ACCOUNT_INFO.getId() + "." + QRCodeFormat.MEMBER_BANKS.getId() + "." + QRCodeFormat.BNB_ID.getId()));
            payload.getParticipant().setReceiving_institution_id(QRCodeFormat.CONSUMER_ACCOUNT_INFO.getId() + "." + QRCodeFormat.SERVICE_CODE.getId());
            payload.getParticipant().setMerchant_id(QRCodeFormat.CONSUMER_ACCOUNT_INFO.getId() + "." + QRCodeFormat.MEMBER_BANKS.getId() + "." + QRCodeFormat.CONSUMER_ID.getId());
            payload.getParticipant().setMerchant_category_code(linkedHashMapQRString.get(QRCodeFormat.MERCHANT_CATEGORY_CODE.getId()));
            payload.getParticipant().setCard_acceptor_id(linkedHashMapQRString.get(QRCodeFormat.CONSUMER_ACCOUNT_INFO.getId() + "." + QRCodeFormat.MEMBER_BANKS.getId() + "." + QRCodeFormat.CONSUMER_ID.getId()));
            payload.getParticipant().setCard_acceptor_name(linkedHashMapQRString.get(QRCodeFormat.CARD_ACCEPTOR_NAME.getId()));
            payload.getParticipant().setCard_acceptor_city(linkedHashMapQRString.get(QRCodeFormat.CARD_ACCEPTOR_CITY.getId()));
            payload.getParticipant().setCard_acceptor_country(linkedHashMapQRString.get(QRCodeFormat.COUNTRY_CODE.getId()));


            payload.setRecipient(recipient);
            payload.getRecipient().setFull_name("Nguyen Xuan Long");

            payload.getRecipient().setAddress(address);
            payload.getRecipient().getAddress().setCountry(linkedHashMapQRString.get(QRCodeFormat.COUNTRY_CODE.getId()));
            payload.getRecipient().getAddress().setPhone("0332067676");
            payload.setRecipient_account(QRCodeFormat.CONSUMER_ID.getId());

            napasResponse.setOrder_info(order_info);
            napasResponse.getOrder_info().setBill_number(CommonConstant.BILL_NUMBER);

            napasResponse.setAdditional_message(linkedHashMapQRString.get(QRCodeFormat.ADDITION_INFO.getId()));

            payload.setPayment(payment);
            napasResponse.setPayload(payload);
        }


        return napasResponse;
    }

    public boolean checkCRC(String qrString) throws UnsupportedEncodingException {
        qrString = qrString.trim();
        if (genCRC(qrString.substring(0, qrString.length() - 4)).equals(qrString.substring(qrString.length() - 4))) {
            return true;
        }
        return false;
    }

    public String genCRC(String qrString) throws UnsupportedEncodingException {

        return Integer.toHexString(crc16(qrString.getBytes("ASCII"))).toUpperCase();

    }

    public int crc16(byte[] value) {
        int crc = 0xFFFF;          // initial value
        int polynomial = 0x1021;   // 0001 0000 0010 0001  (0, 5, 12)

        byte[] testBytes = value;

        //byte[] bytes = args[0].getBytes();

        for (byte b : testBytes) {
            for (int i = 0; i < 8; i++) {
                boolean bit = ((b >> (7 - i) & 1) == 1);
                boolean c15 = ((crc >> 15 & 1) == 1);
                crc <<= 1;
                if (c15 ^ bit) crc ^= polynomial;
            }
        }

        crc &= 0xffff;
        return crc;
    }
}
