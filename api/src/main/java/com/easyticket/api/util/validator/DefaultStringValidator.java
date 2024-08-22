package com.easyticket.api.util.validator;

import org.springframework.stereotype.Component;

@Component
public class DefaultStringValidator implements StringValidator {

    @Override
    public String validate(String value, String defaultValue) {
        return (value != null && !value.trim().isEmpty()) ? value : defaultValue;
    }
}
