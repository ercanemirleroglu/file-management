package com.ercanemirleroglu.etscase.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DocumentDto {
    private Long id;
    private String name;
    private String path;
    private ExtensionDto extension;
    private byte[] data;
}
