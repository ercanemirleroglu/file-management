package com.ercanemirleroglu.etscase.mw.adapter;

import com.ercanemirleroglu.etscase.domain.dto.DocumentDto;
import com.ercanemirleroglu.etscase.domain.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

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
}
