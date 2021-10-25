package com.example.rsaproject.entity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Transactional
public class CardTable {
    @Id
    @SequenceGenerator(
            name = "card_id_seq",
            sequenceName = "card_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "card_id_seq"
    )
    private long id;
    private String card_number;
    private String expired_at;
}
