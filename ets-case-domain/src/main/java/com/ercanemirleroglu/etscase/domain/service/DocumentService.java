package com.ercanemirleroglu.etscase.domain.service;

import com.ercanemirleroglu.etscase.domain.dto.DocumentDto;
import com.ercanemirleroglu.etscase.domain.entity.Document;
import com.ercanemirleroglu.etscase.domain.factory.DocumentFactory;
import com.ercanemirleroglu.etscase.domain.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final DocumentFactory documentFactory;

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

    public void upload(DocumentDto documentDto) {
        Document entity = documentFactory.from(documentDto);
        documentRepository.save(entity);
    }

    public void update(Long id, DocumentDto documentDto) {
        Optional<Document> byId = documentRepository.findById(id);
        if (byId.isEmpty())
            throw new IllegalArgumentException("Document not found with id: " + id);
        Document entity = byId.get();
        entity.update(documentDto);
        documentRepository.save(entity);
    }

    public void delete(Long id) {
        Optional<Document> byId = documentRepository.findById(id);
        if (byId.isEmpty())
            throw new IllegalArgumentException("Document not found with id: " + id);
        Document entity = byId.get();
        documentRepository.delete(entity);
    }
}
