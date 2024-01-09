package com.workintech.s19challenge.entity.user;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "credit_card", schema = "fsweb2")
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "cardNumber")
    private String cardNumber;

    @Column(name = "cvc")
    private String cvc;

    @Column(name = "expirationDate")
    private String expirationDate;

    @Column(name = "nameAndSurname")
    private String nameAndSurname;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;



}
