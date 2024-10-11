package com.converter.api.application.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseEnum {
    SUCCESS_001("P-001", "Operation successful"),
    BAD_REQUEST_001("B-001", "Amount, currency origin, and currency destination are required."),
    BAD_REQUEST_002("B-002", "The provided data is invalid for currency conversion.");
    private final String code;
    private final String message;
}
