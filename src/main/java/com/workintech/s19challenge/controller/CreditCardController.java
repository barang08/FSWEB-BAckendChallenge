package com.workintech.s19challenge.controller;

import com.workintech.s19challenge.dto.CreditCardResponse;
import com.workintech.s19challenge.entity.user.CreditCard;
import com.workintech.s19challenge.entity.user.User;
import com.workintech.s19challenge.exception.GlobalException;
import com.workintech.s19challenge.service.user.CreditCardService;
import com.workintech.s19challenge.service.user.UserOperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/creditCard")
public class CreditCardController {

    private CreditCardService creditCardService;
    private UserOperService userOperService;

    @Autowired
    public CreditCardController(CreditCardService creditCardService,UserOperService userOperService) {
        this.creditCardService = creditCardService;
        this.userOperService = userOperService;
    }

    @GetMapping
    public List<CreditCard> getAll(){
        return creditCardService.findAll();
    }

    @GetMapping("/{id}")
    public CreditCardResponse getCreditCard(@PathVariable long id){
        CreditCard creditCard = creditCardService.findById(id);
        if(creditCard != null){
            return new CreditCardResponse(creditCard.getId(), creditCard.getCardNumber(),creditCard.getCvc(), creditCard.getExpirationDate(),creditCard.getNameAndSurname());
        }
        throw new GlobalException("Credit card is not found with given id: "+id, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{userId}")
    public CreditCardResponse saveCreditCardForUser(@PathVariable long userId, @RequestBody CreditCard newCreditCard) {
        User user = userOperService.findByID(userId);
        if (user != null) {
            newCreditCard.setUser(user);
            CreditCard savedCreditCard = creditCardService.save(newCreditCard);
            return new CreditCardResponse(savedCreditCard.getId(), savedCreditCard.getCardNumber(), savedCreditCard.getCvc(), savedCreditCard.getExpirationDate(), savedCreditCard.getNameAndSurname());
        } else {
            throw new GlobalException("User is not found with given id:" + userId, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public CreditCardResponse update(@PathVariable long id, @RequestBody CreditCard creditCard){
        CreditCard newCreditCard = creditCardService.findById(id);
        if(newCreditCard != null){
            newCreditCard.setCardNumber(creditCard.getCardNumber());
            newCreditCard.setCvc(creditCard.getCvc());
            newCreditCard.setExpirationDate(creditCard.getExpirationDate());
            newCreditCard.setNameAndSurname(creditCard.getNameAndSurname());
            creditCardService.save(newCreditCard);
        }else{
        throw new  GlobalException("Credit card is not found with given id:"+id, HttpStatus.NOT_FOUND);
    }
        return new CreditCardResponse(newCreditCard.getId(), newCreditCard.getCardNumber(), newCreditCard.getCvc(),
                newCreditCard.getExpirationDate(), newCreditCard.getNameAndSurname());
    }



    @DeleteMapping("/{id}")
    public CreditCardResponse delete(@PathVariable long id){
        CreditCard creditCard = creditCardService.findById(id);
        creditCardService.delete(id);
        return new CreditCardResponse(creditCard.getId(), creditCard.getCardNumber(), creditCard.getCvc(), creditCard.getExpirationDate(), creditCard.getNameAndSurname());
    }

}
