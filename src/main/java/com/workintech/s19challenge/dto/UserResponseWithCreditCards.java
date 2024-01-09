package com.workintech.s19challenge.dto;

import com.workintech.s19challenge.entity.user.CreditCard;

import java.util.List;

public record UserResponseWithCreditCards(String name, String email, List<CreditCard> creditCardList) {
}
