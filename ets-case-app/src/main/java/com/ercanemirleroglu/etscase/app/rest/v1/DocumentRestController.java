package com.ercanemirleroglu.etscase.app.rest.v1;

import com.ercanemirleroglu.etscase.mw.adapter.DocumentAdapter;
import com.ercanemirleroglu.etscase.mw.model.response.DocumentListResponse;
import com.ercanemirleroglu.etscase.mw.model.response.DocumentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping(path = "/api/v1/document")
@RequiredArgsConstructor
public class DocumentRestController {

    private final DocumentAdapter documentAdapter;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    private ResponseEntity<Void> upload(@RequestPart(value = "files") MultipartFile[] multipartFiles) {
        documentAdapter.uploadDocument(multipartFiles);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    private ResponseEntity<Void> update(@PathVariable Long id,
                                        @RequestPart(value = "files") MultipartFile[] multipartFiles) {
        documentAdapter.updateDocument(id, multipartFiles);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/{id}")
    private ResponseEntity<Void> delete(@PathVariable Long id) {
        documentAdapter.deleteDocument(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    private ResponseEntity<DocumentListResponse> getAll() {
        return ResponseEntity.ok().body(documentAdapter.getAllDocuments());
    }

    @GetMapping(path = "/{id}")
    private ResponseEntity<DocumentResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(documentAdapter.getById(id));
    }

    @PostMapping(path = "/{id}/download")
    public ResponseEntity<Resource> download(@PathVariable Long id) {
        return documentAdapter.download(id);
    }

}
