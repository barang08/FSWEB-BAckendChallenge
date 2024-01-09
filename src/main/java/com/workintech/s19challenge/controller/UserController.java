package com.workintech.s19challenge.controller;


import com.workintech.s19challenge.dto.UserResponse;
import com.workintech.s19challenge.dto.UserResponseWithCreditCards;
import com.workintech.s19challenge.entity.user.CreditCard;
import com.workintech.s19challenge.entity.user.User;
import com.workintech.s19challenge.exception.GlobalException;
import com.workintech.s19challenge.service.user.AuthenticationService;
import com.workintech.s19challenge.service.user.UserOperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserOperService userOperService;


    @Autowired
    public UserController(UserOperService userOperService) {
        this.userOperService = userOperService;

    }

    @GetMapping
    public List<User> findAll(){
        return userOperService.findAll();
    }


    @GetMapping("/{id}")
    public UserResponseWithCreditCards findById(@PathVariable long id){
        User user = userOperService.findByID(id);
        List<CreditCard> creditCardList = new ArrayList<>();
        user.getCreditCardList().forEach(creditCard -> {
            creditCardList.add(creditCard);
        });
        return new UserResponseWithCreditCards(user.getName(), user.getEmail(), creditCardList);
    }

    @PutMapping("/{id}")
    public UserResponse update(@PathVariable long id, @RequestBody User user){
        User foundUser= userOperService.findByID(id);
        if(foundUser != null){
            foundUser.setName(user.getName());
            foundUser.setPassword(user.getPassword());
            foundUser.setEmail(user.getEmail());
            userOperService.save(foundUser);
        }else{
            throw new  GlobalException("User is not found with given id:"+id, HttpStatus.NOT_FOUND);
        }
        return new UserResponse(foundUser.getId(), foundUser.getName(), foundUser.getEmail());
    }

    @DeleteMapping("/{id}")
    public UserResponse delete(@PathVariable long id){
        User user1 = userOperService.findByID(id);
        userOperService.delete(id);
        return new UserResponse(user1.getId(), user1.getName(), user1.getEmail());
    }

}
