package com.ercanemirleroglu.etscase.domain.dto;

import java.util.Arrays;

public enum ExtensionDto {
    PDF(".pdf"), JPEG(".jpeg"), JPG(".jpg"), DOCX(".docx"), XLSX(".xlsx"), PNG(".png");

    private final String displayValue;

    ExtensionDto(String s) {
        displayValue = s;
    }

    public String getDisplayValue(){
        return displayValue;
    }

    public static ExtensionDto controlAndGet(String s) {
        return Arrays.stream(ExtensionDto.values()).filter(ext -> ext.displayValue.equals(s)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Not support extension!"));
    }
}
