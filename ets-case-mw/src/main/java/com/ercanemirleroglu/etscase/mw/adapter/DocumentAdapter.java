package com.ercanemirleroglu.etscase.mw.adapter;

import com.ercanemirleroglu.etscase.domain.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DocumentAdapter {

    private final DocumentService documentService;
}
