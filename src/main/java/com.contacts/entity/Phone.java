package com.contacts.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Phone {
    private Integer id;
    private String prefix;
    private String telType;
    private String code;
    private String note;
    private String fullNumber;
    private Integer personId;
}
