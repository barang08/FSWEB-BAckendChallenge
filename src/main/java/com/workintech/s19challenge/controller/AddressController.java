package com.workintech.s19challenge.controller;

import com.workintech.s19challenge.dto.AddressRequest;
import com.workintech.s19challenge.dto.AddressResponse;
import com.workintech.s19challenge.entity.user.Address;
import com.workintech.s19challenge.entity.user.User;
import com.workintech.s19challenge.exception.GlobalException;
import com.workintech.s19challenge.service.user.AddressService;
import com.workintech.s19challenge.service.user.UserOperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    private AddressService addressService;
private UserOperService userOperService;
    @Autowired
    public AddressController(AddressService addressService,UserOperService userOperService) {
        this.addressService = addressService;
        this.userOperService = userOperService;
    }



    @GetMapping
    public List<Address> findAll(){
        return addressService.findAll();
    }

    @GetMapping("/{id}")
    public AddressResponse addressResponse(@PathVariable long id){
        Address address = addressService.findById(id);
        if(address != null){
            return new AddressResponse(address.getId(), address.getCountry(), address.getCity());
        }
        throw new GlobalException("Address is not found with given id:"+id, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{userId}")
    public AddressResponse save(@PathVariable long userId, @RequestBody Address address) {
        User user = userOperService.findByID(userId);
        if (user != null) {
            user.getAddresses().add(address);
            address.setUser(user);
            address = addressService.save(address);
            return new AddressResponse(address.getId(), address.getCity(), address.getCountry());
        } else {
            throw new GlobalException("User is not found with given id:" + userId, HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/{id}")
    public AddressResponse updateAddress(@PathVariable long id, @RequestBody AddressRequest updatedAddress) {
        Address existingAddress = addressService.findById(id);

        if (existingAddress != null) {
            existingAddress.setCountry(updatedAddress.country());
            existingAddress.setCity(updatedAddress.city());

            Address updated = addressService.save(existingAddress);
            return new AddressResponse(updated.getId(), updated.getCountry(), updated.getCity());
        } else {

            throw new GlobalException("Address is not found with given id: "+id,HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Address> delete(@PathVariable long id){
        Address address = addressService.findById(id);
        if(address!= null){
            addressService.delete(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }



}
