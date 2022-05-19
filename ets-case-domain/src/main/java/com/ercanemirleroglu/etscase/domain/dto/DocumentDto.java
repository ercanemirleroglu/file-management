package com.ercanemirleroglu.etscase.domain.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Data
@Builder
public class DocumentDto {
    private Long id;
    private String name;
    private String path;
    private Long size;
    private ExtensionDto extension;
    private byte[] data;

    public static DocumentDto from(MultipartFile file) throws IOException {
        String[] split = file.getOriginalFilename().split("\\.");
        DocumentDto documentDto = DocumentDto.builder()
                .name(split[0])
                .extension(ExtensionDto.controlAndGet("." + split[1]))
                .size(file.getSize())
                .data(file.getBytes())
                .build();
        return documentDto;
    }
}
