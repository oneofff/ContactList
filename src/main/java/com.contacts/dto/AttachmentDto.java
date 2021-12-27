package com.contacts.dto;

import com.contacts.entity.Attachment;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class AttachmentDto {
    @JsonProperty
    private Integer id;
    @JsonProperty
    private String name;
    @JsonProperty
    private Date downloadTime;
    @JsonProperty
    private String note;
    @JsonProperty
    private String src;

    public AttachmentDto(Attachment attachment) {
        this.id = attachment.getId();
        this.name = attachment.getName();
        this.downloadTime= attachment.getDownloadTime();
        this.note = attachment.getNote();
        this.src= attachment.getSrc();
    }

    public AttachmentDto() {
    }
}
