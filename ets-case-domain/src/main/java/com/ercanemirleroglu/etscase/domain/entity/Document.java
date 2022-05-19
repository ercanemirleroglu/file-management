package com.ercanemirleroglu.etscase.domain.entity;

import com.ercanemirleroglu.etscase.domain.dto.DocumentDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "document")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Document {
    @Id
    @GeneratedValue
    private Long id;

    private String path;

    private String name;

    @Enumerated(EnumType.STRING)
    private Extension extension;

    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] data;

    public DocumentDto dtoWithoutData(){
        return DocumentDto.builder()
                .id(this.id)
                .extension(this.extension.dto())
                .name(this.name)
                .path(this.path)
                .build();
    }

    public DocumentDto dto(){
        DocumentDto documentDto = dtoWithoutData();
        documentDto.setData(this.data);
        return documentDto;
    }

}
