package com.contacts.dto;

import com.contacts.entity.Contact;
import com.contacts.entity.enums.Gender;
import com.contacts.entity.enums.MaritalStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@ToString

public class ContactDto {
    private Integer id;
    private String name;
    private String surname;
    private String patronymic;
    @JsonFormat(pattern = "dd:MM:yyyy")
    private Date birthDate;
    private Gender gender;
    private String citizenship;
    private MaritalStatus maritalStatus;
    private String email;
    private String company;
    private String website;
    private String country;
    private String city;
    private String address;
    private String postIndex;
    private List<AttachmentDto> attachmentDtoList;
    private List<PhoneDto> phoneDtoList;

    public ContactDto(Contact contact) {
        this.id = contact.getId();
        this.name = contact.getName();
        this.surname = contact.getSurname();
        this.patronymic = contact.getPatronymic();
        this.birthDate = contact.getBirthDate();
        this.gender = contact.getGender();
        this.citizenship = contact.getCitizenship();
        this.maritalStatus = contact.getMaritalStatus();
        this.email = contact.getEmail();
        this.company = contact.getCompany();
        this.website = contact.getWebsite();
        this.country = contact.getCountry();
        this.city = contact.getCity();
        this.address = contact.getAddress();
        this.postIndex = contact.getPostIndex();
        if (contact.getAttachmentList() != null) {
            List<AttachmentDto> attachmentDtoList = new ArrayList<>();
            for (var item :
                    contact.getAttachmentList()) {
                AttachmentDto attachmentDto = new AttachmentDto(item);
                attachmentDtoList.add(attachmentDto);
            }
            this.attachmentDtoList = attachmentDtoList;
        } else {
            this.attachmentDtoList = new ArrayList<AttachmentDto>();
        }
        if (contact.getPhoneList() != null) {
            List<PhoneDto> phoneDtoList = new ArrayList<>();
            for (var item :
                    contact.getPhoneList()) {
                PhoneDto phoneDto = new PhoneDto(item);
                phoneDtoList.add(phoneDto);
            }
        } else {
            this.phoneDtoList = new ArrayList<PhoneDto>();
        }
    }

    public ContactDto(Integer id, String name, String surname, String patronymic, Date birthDate, Gender gender, String citizenship, MaritalStatus maritalStatus, String email, String company, String website, String country, String city, String address, String postIndex, List<AttachmentDto> attachmentDtoList, List<PhoneDto> phoneDtoList) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.birthDate = birthDate;
        this.gender = gender;
        this.citizenship = citizenship;
        this.maritalStatus = maritalStatus;
        this.email = email;
        this.company = company;
        this.website = website;
        this.country = country;
        this.city = city;
        this.address = address;
        this.postIndex = postIndex;
        this.attachmentDtoList = attachmentDtoList;
        this.phoneDtoList = phoneDtoList;
    }

    public String writeOnJSON() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("");
        }
    }

}
