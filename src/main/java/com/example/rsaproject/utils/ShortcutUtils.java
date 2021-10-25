package com.example.rsaproject.utils;

import com.example.rsaproject.form.ErrorResponseForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;

@Component
public class ShortcutUtils {


    public ErrorResponseForm getValidationErrors(BindingResult result) {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < result.getFieldErrorCount(); i++) {
            map.put(result.getFieldErrors().get(i).getField(), result.getFieldErrors().get(i).getDefaultMessage());
        }
        return new ErrorResponseForm("Validation errors", map);
    }

}
