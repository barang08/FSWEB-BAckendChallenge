package com.workintech.s19challenge.dto;

public record CreditCardResponse(int id,String cardNumber,String cvc,String expirationDate,String nameAndSurname) {
}
