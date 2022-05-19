package com.ercanemirleroglu.etscase.mw.adapter;

import com.ercanemirleroglu.etscase.domain.dto.DocumentDto;
import com.ercanemirleroglu.etscase.domain.service.DocumentService;
import com.ercanemirleroglu.etscase.mw.mapper.DocumentMapper;
import com.ercanemirleroglu.etscase.mw.model.response.DocumentListResponse;
import com.ercanemirleroglu.etscase.mw.model.response.DocumentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DocumentAdapter {

    private final DocumentService documentService;

    @Transactional
    public void uploadDocument(MultipartFile[] multipartFiles) {
        if (Objects.nonNull(multipartFiles)) {
            Arrays.stream(multipartFiles).forEach(file -> {
                try {
                    DocumentDto documentDto = DocumentDto.from(file);
                    documentService.upload(documentDto);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    @Transactional
    public void updateDocument(Long id, MultipartFile[] multipartFiles) {
        if (Objects.nonNull(multipartFiles)) {
            Arrays.stream(multipartFiles).forEach(file -> {
                try {
                    DocumentDto documentDto = DocumentDto.from(file);
                    documentService.update(id, documentDto);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public void deleteDocument(Long id) {
        documentService.delete(id);
    }

    public DocumentListResponse getAllDocuments() {
        List<DocumentResponse> documentInfos = documentService.getAll().stream().map(DocumentMapper::toResponse)
                .collect(Collectors.toList());
        return DocumentListResponse.builder()
                .documentInfos(documentInfos)
                .build();
    }

    public DocumentResponse getById(Long id) {
        DocumentDto oneById = documentService.getOneById(id);
        return DocumentMapper.toResponse(oneById);
    }
}
