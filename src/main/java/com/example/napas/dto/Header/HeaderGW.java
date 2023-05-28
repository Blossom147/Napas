package com.example.napas.dto.Header;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class HeaderGW {

    @NotBlank
    @Length(min = 6 , max = 6)
    private String bkCd;

    @NotBlank
    private String brCd ;

    @NotBlank
    private String trnDt;

    @NotBlank
    private String direction;

    @NotBlank
    private String reqResGb;

    @NotBlank
    private String refNo;

    private String errCode;

    private String errDesc;


}
