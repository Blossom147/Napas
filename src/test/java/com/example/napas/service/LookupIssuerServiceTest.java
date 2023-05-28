package com.example.napas.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
class LookupIssuerServiceTest {
    @Autowired
    private LookupIssuerService qrService;

    @Test
    public void testCheckCRC() throws UnsupportedEncodingException {
       String qrString = "00020101021138480010A00000072701300006970403011621129950446040255204581253037045802VN5910PHUONGGCAC6005HANOI62110307NPS68696304";
            String a = Integer.toHexString(qrService.crc16(qrString.getBytes("ASCII"))).toUpperCase();
        System.out.println(a);
    }
}