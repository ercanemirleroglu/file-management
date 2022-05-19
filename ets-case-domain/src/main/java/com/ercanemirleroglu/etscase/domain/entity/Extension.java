package com.ercanemirleroglu.etscase.domain.entity;

import com.ercanemirleroglu.etscase.domain.dto.ExtensionDto;

public enum Extension {
    PDF(".pdf"), JPEG(".jpeg"), JPG(".jpg"), DOCX(".docx"), XLSX(".xlsx"), PNG(".png");

    private final String displayValue;

    Extension(String s) {
        this.displayValue = s;
    }

    public ExtensionDto dto() {
        return ExtensionDto.valueOf(this.toString());
    }
}
