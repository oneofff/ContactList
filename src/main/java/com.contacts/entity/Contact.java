package com.contacts.entity;

import com.contacts.entity.enums.Gender;
import com.contacts.entity.enums.MaritalStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;


@Builder
@Getter
@Setter
public class Contact {
    private Integer id;
    private  String name;
    private String surname;
    private  String patronymic;
    private Date birthDate;
    private Gender gender;
    private String citizenship;
    private MaritalStatus maritalStatus;
    private String email;
    private String company;
    private String website;
    private String country;
    private  String city;
    private String address;
    private String postIndex;
    private List<Attachment> attachmentList;
    private  List<Phone> phoneList;
}
