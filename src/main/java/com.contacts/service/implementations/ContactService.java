package com.contacts.service.implementations;

import com.contacts.dto.AttachmentDto;
import com.contacts.dto.ContactDto;
import com.contacts.entity.Attachment;
import com.contacts.entity.Contact;
import com.contacts.service.interfaces.IContactService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.implementations.ContactDao;

import java.util.ArrayList;
import java.util.List;

public class ContactService implements IContactService {

    ContactDao contactDao = new ContactDao();

    @Override
    public String getContactsJSON() {
        List<Contact> contactList = new ArrayList<>(contactDao.getContactList());
        List<ContactDto> contactDtoList = new ArrayList<>();
        Attachment attachment =  Attachment.builder().id(1).name("newAttachment").note("HI").personId(2).src("here").build();
        List<Attachment> attachmentList = new ArrayList<>();
        attachmentList.add(attachment);
        contactList.get(0).setAttachmentList(attachmentList);
        for (var item :
                contactList) {
            ContactDto contactDto = new ContactDto(item);
            contactDtoList.add(contactDto);
        }
        try {
            return new ObjectMapper().writeValueAsString(contactDtoList);
        } catch (JsonProcessingException e) {
           throw new RuntimeException("Error while marshaling json");
        }
    }
}
