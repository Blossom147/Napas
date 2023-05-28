package com.example.napas.service;

import com.example.napas.constant.CommonConstant;
import com.example.napas.constant.ErrorDefination;
import com.example.napas.constant.QRCodeFormat;
import com.example.napas.dto.Header.HeaderNapas;
import com.example.napas.dto.request.Payment.PaymentRequestNapas;
import com.example.napas.dto.response.LookupIssuer.NapasResponse;
import com.example.napas.dto.response.Payment.PaymentResponseNapas;
import com.example.napas.exception.ValidationHelper;
import com.example.napas.repo.BankRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.List;

@Slf4j
@Service
public class PaymentService {
    @Autowired
    BankRepo bankRepo;


    public PaymentResponseNapas genResNapas(PaymentRequestNapas paymentRequestNapas) throws UnsupportedEncodingException {
        PaymentResponseNapas paymentResponseNapas = new PaymentResponseNapas();

        PaymentResponseNapas.Payment payment = new PaymentResponseNapas.Payment();
        PaymentResponseNapas.Payload payload = new PaymentResponseNapas.Payload();
        PaymentResponseNapas.Recipient recipient = new PaymentResponseNapas.Recipient();
        PaymentResponseNapas.Participant participant = new PaymentResponseNapas.Participant();
        PaymentResponseNapas.AdditionalInfo additionalInfo = new PaymentResponseNapas.AdditionalInfo();
        PaymentResponseNapas.Address address = new PaymentResponseNapas.Address();
        PaymentResponseNapas.Instruction instruction = new PaymentResponseNapas.Instruction();
        PaymentResponseNapas.Sender sender = new PaymentResponseNapas.Sender();
        PaymentResponseNapas.OrderInfo order_info = new PaymentResponseNapas.OrderInfo();

        // Payload
        paymentResponseNapas.setPayload(payload);
        payload.setAmount(paymentRequestNapas.getPayload().getAmount());
        payload.setCurrency(paymentRequestNapas.getPayload().getCurrency());
        payload.setSender_account(paymentRequestNapas.getPayload().getSender_account());
        payload.setAdditional_message(paymentRequestNapas.getPayload().getAdditional_message());

    // Payment
        paymentResponseNapas.getPayload().setPayment(payment);
        payment.setType(CommonConstant.PAYMENT_TYPE);
        payment.setGeneration_method(paymentRequestNapas.getPayload().getPayment().getGeneration_method());
        payment.setChannel(paymentRequestNapas.getPayload().getPayment().getChannel());
        payment.setPayment_reference(paymentRequestNapas.getPayload().getPayment().getPayment_reference());
        payment.setDevice_id(paymentRequestNapas.getPayload().getPayment().getDevice_id());
        payment.setLocation(paymentRequestNapas.getPayload().getPayment().getLocation());
        payment.setTransaction_local_date_time(paymentRequestNapas.getPayload().getPayment().getTransaction_local_date_time());
        payment.setInterbank_amount(paymentRequestNapas.getPayload().getPayment().getInterbank_amount());
        payment.setInterbank_currency(paymentRequestNapas.getPayload().getPayment().getInterbank_currency());
        payment.setExchange_rate(paymentRequestNapas.getPayload().getPayment().getExchange_rate());
        payment.setIndicator(paymentRequestNapas.getPayload().getPayment().getIndicator());
        payment.setFee_fixed(paymentRequestNapas.getPayload().getPayment().getFee_fixed());
        payment.setFee_percentage(paymentRequestNapas.getPayload().getPayment().getFee_percentage());
        payment.setPayment_reference(paymentRequestNapas.getPayload().getPayment().getPayment_reference());
        payment.setEnd_to_end_reference(paymentRequestNapas.getPayload().getPayment().getEnd_to_end_reference());
        payment.setTrace(paymentRequestNapas.getPayload().getPayment().getTrace());

        // Sender

        paymentResponseNapas.getPayload().setSender(sender);
        sender.setFirst_name(paymentRequestNapas.getPayload().getSender().getFirst_name());
        sender.setMiddle_name(paymentRequestNapas.getPayload().getSender().getMiddle_name());
        sender.setLast_name(paymentRequestNapas.getPayload().getSender().getLast_name());
        sender.setFull_name(paymentRequestNapas.getPayload().getSender().getFull_name());
        sender.setDate_of_birth(paymentRequestNapas.getPayload().getSender().getDate_of_birth());


        // Participant

        paymentResponseNapas.getPayload().setParticipant(participant);
        participant.setCard_acceptor_city(paymentRequestNapas.getPayload().getParticipant().getCard_acceptor_city());
        participant.setCard_acceptor_country(paymentRequestNapas.getPayload().getParticipant().getCard_acceptor_country());
        participant.setCard_acceptor_id(paymentRequestNapas.getPayload().getParticipant().getCard_acceptor_id());
        participant.setCard_acceptor_name(paymentRequestNapas.getPayload().getParticipant().getCard_acceptor_name());
        participant.setCard_language_preference(paymentRequestNapas.getPayload().getParticipant().getCard_language_preference());
        participant.setCard_name_alternate_language(paymentRequestNapas.getPayload().getParticipant().getCard_name_alternate_language());
        participant.setCard_payment_system_specific(paymentRequestNapas.getPayload().getParticipant().getCard_payment_system_specific());
        participant.setCard_postal_code(paymentRequestNapas.getPayload().getParticipant().getCard_postal_code());
        participant.setReceiving_institution_id(paymentRequestNapas.getPayload().getParticipant().getReceiving_institution_id());
        participant.setOriginating_institution_id(paymentRequestNapas.getPayload().getParticipant().getOriginating_institution_id());
        participant.setMerchant_id(paymentRequestNapas.getPayload().getParticipant().getMerchant_id());
        participant.setMerchant_category_code(paymentRequestNapas.getPayload().getParticipant().getMerchant_category_code());
        participant.setCity_alternate_language(paymentRequestNapas.getPayload().getParticipant().getCard_city_alternate_language());

        // Recipient

        paymentResponseNapas.getPayload().setRecipient(recipient);
        recipient.setFirst_name(paymentRequestNapas.getPayload().getRecipient().getFirst_name());
        recipient.setFull_name(paymentRequestNapas.getPayload().getRecipient().getFull_name());
        recipient.setLast_name(paymentRequestNapas.getPayload().getRecipient().getLast_name());
        recipient.setDate_of_birth(paymentRequestNapas.getPayload().getRecipient().getDate_of_birth());
        recipient.setMiddle_name(paymentRequestNapas.getPayload().getRecipient().getMiddle_name());

        // Address

        paymentResponseNapas.getPayload().getRecipient().setAddress(address);
        address.setLine1(paymentRequestNapas.getPayload().getSender().getAddress().getLine1());
        address.setLine2(paymentRequestNapas.getPayload().getSender().getAddress().getLine2());
        address.setCity(paymentRequestNapas.getPayload().getSender().getAddress().getCity());
        address.setCountry(paymentRequestNapas.getPayload().getSender().getAddress().getCountry());
        address.setEmail(paymentRequestNapas.getPayload().getSender().getAddress().getEmail());
        address.setCountry_subdivision(paymentRequestNapas.getPayload().getSender().getAddress().getCountry_subdivision());
        address.setPhone(paymentRequestNapas.getPayload().getSender().getAddress().getPhone());
        address.setPostal_code(paymentRequestNapas.getPayload().getSender().getAddress().getPostal_code());


        // Order_Info

        paymentResponseNapas.setOrder_info(order_info);
        order_info.setBill_number(paymentRequestNapas.getPayload().getOrder_info().getBill_number());
        order_info.setCustomer_label(paymentRequestNapas.getPayload().getOrder_info().getCustomer_label());
        order_info.setAdditional_data_request(paymentRequestNapas.getPayload().getOrder_info().getAdditional_data_request());
        order_info.setMobile_number(paymentRequestNapas.getPayload().getOrder_info().getMobile_number());
        order_info.setLoyalty_number(paymentRequestNapas.getPayload().getOrder_info().getLoyalty_number());
        order_info.setStore_label(paymentRequestNapas.getPayload().getOrder_info().getStore_label());
        order_info.setTerminal_label(paymentRequestNapas.getPayload().getOrder_info().getTerminal_label());
        order_info.setTransaction_purpose(paymentRequestNapas.getPayload().getOrder_info().getTransaction_purpose());


        paymentResponseNapas.setAdditional_info(additionalInfo);




        return paymentResponseNapas;
    }




}

