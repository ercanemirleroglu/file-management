package com.ercanemirleroglu.etscase.domain.factory;

import com.ercanemirleroglu.etscase.domain.dto.DocumentDto;
import com.ercanemirleroglu.etscase.domain.entity.Document;
import com.ercanemirleroglu.etscase.domain.entity.Extension;
import org.springframework.stereotype.Component;

@Component
public class DocumentFactory {

    public Document from(DocumentDto documentDto) {
        return Document.builder()
                .name(documentDto.getName())
                .path(documentDto.getPath())
                .extension(Extension.valueOf(documentDto.getExtension().name()))
                .data(documentDto.getData())
                .build();
    }
}
