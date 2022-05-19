package com.ercanemirleroglu.etscase.domain.dto;

public enum ExtensionDto {
    PDF(".pdf"), JPEG(".jpeg"), JPG(".jpg"), DOCX(".docx"), XLSX(".xlsx");

    private final String displayValue;

    ExtensionDto(String s) {
        this.displayValue = s;
    }
}
