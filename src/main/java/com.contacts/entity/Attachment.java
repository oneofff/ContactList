package com.contacts.entity;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;
@Builder
@Getter
public class Attachment {
    private Integer id;
    private Integer personId;
    private String name;
    private Date downloadTime;
    private  String note;
    private String src;
}
