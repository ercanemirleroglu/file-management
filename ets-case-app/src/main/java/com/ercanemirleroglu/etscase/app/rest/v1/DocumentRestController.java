package com.ercanemirleroglu.etscase.app.rest.v1;

import com.ercanemirleroglu.etscase.mw.adapter.DocumentAdapter;
import lombok.RequiredArgsConstructor;
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

    @PutMapping(path = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    private ResponseEntity<Void> delete(@PathVariable Long id) {
        documentAdapter.deleteDocument(id);
        return ResponseEntity.ok().build();
    }

}
