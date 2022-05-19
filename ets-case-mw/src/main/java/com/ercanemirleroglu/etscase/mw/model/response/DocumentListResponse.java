package com.ercanemirleroglu.etscase.mw.model.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
public class DocumentListResponse {
    private List<DocumentResponse> documentInfos;
}
