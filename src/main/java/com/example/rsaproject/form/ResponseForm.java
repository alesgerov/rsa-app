package com.example.rsaproject.form;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NotBlank
public class ResponseForm {
    private int code;
    private Object notes;
}
