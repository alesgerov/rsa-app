package com.example.rsaproject.service;

import com.example.rsaproject.entity.CardTable;
import com.example.rsaproject.exception.CardAlreadyExistsException;
import com.example.rsaproject.form.CardForm;
import com.example.rsaproject.repository.CardRepository;
import com.example.rsaproject.utils.RsaUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardService {

    private final CardRepository cardRepository;
    private final RsaUtils rsaUtils;


    public CardService(CardRepository cardRepository, RsaUtils rsaUtils) {
        this.cardRepository = cardRepository;
        this.rsaUtils = rsaUtils;
    }

    public Optional<CardTable> getCardByNumber(String cardNumber){
        return cardRepository.findByCard_number(cardNumber);
    }

    private CardForm saveCard(CardForm cardForm) throws Exception {
        Optional<CardTable> optional=getCardByNumber(cardForm.getCardNumber());
        if (optional.isPresent()) throw new CardAlreadyExistsException("Card Exists");
        CardTable cardTable=new CardTable();
        cardTable.setCard_number(cardForm.getCardNumber());
        cardTable.setExpired_at(cardForm.getExpiredAt());
        cardTable=cardRepository.save(cardTable);
        if (cardTable==null) throw new Exception("Unexpected error happened while saving");
        return cardForm;
    }

    public void saveCard(String encoded) throws Exception {
        String decoded=rsaUtils.decrypt(encoded);
        List<String> strings= List.of(decoded.split(" "));
        CardForm form=new CardForm();
        form.setCardNumber(strings.get(0));
        form.setExpiredAt(strings.get(1));
        saveCard(form);
    }
}
