package com.example.rsaproject.controller;


import com.example.rsaproject.form.CardForm;
import com.example.rsaproject.form.ResponseForm;
import com.example.rsaproject.service.CardService;
import com.example.rsaproject.utils.RsaUtils;
import com.example.rsaproject.utils.ShortcutUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api/")
@RestController
public class MainController {

    private final CardService cardService;
    private final ShortcutUtils shortcutUtils;
    private final RsaUtils rsaUtils;


    public MainController(CardService cardService, ShortcutUtils shortcutUtils, RsaUtils rsaUtils) {
        this.cardService = cardService;
        this.shortcutUtils = shortcutUtils;
        this.rsaUtils = rsaUtils;
    }

    @PostMapping(value = {"/add/", "/add"})
    public ResponseEntity<?> addCart(@Valid @RequestBody CardForm form,
                                     BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(422).body(shortcutUtils.getValidationErrors(result));
        }
        try {
            String forEncrypt=form.getCardNumber()+" "+form.getExpiredAt();
            String enc=rsaUtils.encrypt(forEncrypt);
            return ResponseEntity.status(201).body(new ResponseForm(0, enc));
        } catch (Exception e) {
            return ResponseEntity.status(409).body(new ResponseForm(1, e.getMessage()));
        }
    }
    @PostMapping(value = {"/add/enc"})
    public ResponseEntity<?> addCart(@RequestParam("value") String value) {

        try {
            cardService.saveCard(value);
            return ResponseEntity.status(201).body(new ResponseForm(0, "Card saved"));
        } catch (Exception e) {
            return ResponseEntity.status(409).body(new ResponseForm(1, e.getMessage()));
        }
    }
}