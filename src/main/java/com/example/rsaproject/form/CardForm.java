package com.example.rsaproject.form;


import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CardForm {
    @NotBlank
    private String cardNumber;
    @NotBlank
    private String expiredAt;
}
