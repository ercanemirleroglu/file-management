package com.ercanemirleroglu.etscase.mw.mapper;

import com.ercanemirleroglu.etscase.domain.dto.DocumentDto;
import com.ercanemirleroglu.etscase.mw.model.response.DocumentResponse;

public class DocumentMapper {
    public static DocumentResponse toResponse(DocumentDto documentDto){
        return DocumentResponse.builder()
                .id(documentDto.getId())
                .extension(documentDto.getExtension().getDisplayValue())
                .size(documentDto.getSize())
                .name(documentDto.getName())
                .build();
    }
}
