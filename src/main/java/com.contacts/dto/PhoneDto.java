package com.contacts.dto;

import com.contacts.entity.Phone;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PhoneDto {
    @JsonProperty
    private Integer id;
    @JsonProperty
    private String prefix;
    @JsonProperty
    private String telType;
    @JsonProperty
    private String code;
    @JsonProperty
    private  String note;
    @JsonProperty
    private  String fullNumber;

    public PhoneDto(Phone phone) {
        this.id= phone.getId();
        this.prefix= phone.getPrefix();
        this.telType= phone.getTelType();
        this.code= phone.getCode();
        this.note=phone.getNote();
        this.fullNumber=phone.getFullNumber();
    }

    public PhoneDto() {
    }
}
