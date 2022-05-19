package com.ercanemirleroglu.etscase.mw.adapter;

import com.ercanemirleroglu.etscase.domain.dto.DocumentDto;
import com.ercanemirleroglu.etscase.domain.service.DocumentService;
import com.ercanemirleroglu.etscase.mw.mapper.DocumentMapper;
import com.ercanemirleroglu.etscase.mw.model.response.DocumentListResponse;
import com.ercanemirleroglu.etscase.mw.model.response.DocumentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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

    public ResponseEntity<Resource> download(Long id) {
        Optional<DocumentDto> documentById = Optional.ofNullable(documentService.getOneById(id));
        return documentById.<ResponseEntity<Resource>>map(documentDto -> ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +
                        documentDto.getName() + documentDto.getExtension().getDisplayValue() + "\"")
                .body(new ByteArrayResource(documentDto.getData()))).orElseGet(() -> ResponseEntity.notFound().build());

    }
}
