package com.ercanemirleroglu.etscase.domain.entity;

public enum Extension {
    PDF(".pdf"), JPEG(".jpeg"), JPG(".jpg"), DOCX(".docx"), XLSX(".xlsx");

    private final String displayValue;

    Extension(String s) {
        this.displayValue = s;
    }
}
