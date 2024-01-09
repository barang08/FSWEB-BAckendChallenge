package com.workintech.s19challenge.service.user;

import com.workintech.s19challenge.entity.user.Address;
import com.workintech.s19challenge.entity.user.CreditCard;
import com.workintech.s19challenge.exception.GlobalException;
import com.workintech.s19challenge.repository.user.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CreditCardImpl implements CreditCardService{

    private CreditCardRepository creditCardRepository;

    @Autowired
    public CreditCardImpl(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    @Override
    public List<CreditCard> findAll() {
        return creditCardRepository.findAll();
    }

    @Override
    public CreditCard findById(long id) {
        Optional<CreditCard> optionalCreditCard = creditCardRepository.findById(id);
        if(optionalCreditCard.isPresent()){
            return optionalCreditCard.get();
        }
        throw new GlobalException("Credit card is not found with id: " + id, HttpStatus.NOT_FOUND);
    }

    @Override
    public CreditCard save(CreditCard creditCard) {
        return creditCardRepository.save(creditCard);
    }

    @Override
    public CreditCard delete(long id) {
        CreditCard creditCard = findById(id);
        if(creditCard != null){
            creditCardRepository.delete(creditCard);
            return creditCard;
        }
        throw new GlobalException("Credit card is not found with id: " + id, HttpStatus.NOT_FOUND);
    }
}
