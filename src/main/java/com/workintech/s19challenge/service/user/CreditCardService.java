package com.workintech.s19challenge.service.user;

import com.workintech.s19challenge.entity.user.Address;
import com.workintech.s19challenge.entity.user.CreditCard;

import java.util.List;

public interface CreditCardService {

    List<CreditCard> findAll();
    CreditCard findById(long id);
    CreditCard save(CreditCard creditCard);
    CreditCard delete(long id);
}
