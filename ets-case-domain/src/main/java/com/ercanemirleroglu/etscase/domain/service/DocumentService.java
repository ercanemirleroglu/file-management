package com.ercanemirleroglu.etscase.domain.service;

import com.ercanemirleroglu.etscase.domain.dto.DocumentDto;
import com.ercanemirleroglu.etscase.domain.entity.Document;
import com.ercanemirleroglu.etscase.domain.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;

    public List<DocumentDto> getAll(){
        return documentRepository.findAll()
                .stream().map(Document::dto)
                .collect(Collectors.toList());
    }

    public DocumentDto getOneById(Long id) {
        return documentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found doc wit id: " + id))
                .dto();
    }
}
