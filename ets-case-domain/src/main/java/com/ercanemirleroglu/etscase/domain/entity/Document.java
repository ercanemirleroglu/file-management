package com.ercanemirleroglu.etscase.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "document")
@Getter
@NoArgsConstructor
@AllArgsConstructor
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

}
