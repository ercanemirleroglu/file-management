package com.ercanemirleroglu.etscase.mw.model.response;

import lombok.*;

@Data
@Builder
public class DocumentResponse {
    private Long id;
    private String name;
    private String extension;
    private Long size;
}
