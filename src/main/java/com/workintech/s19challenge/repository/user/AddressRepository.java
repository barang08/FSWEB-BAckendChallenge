package com.workintech.s19challenge.repository.user;

import com.workintech.s19challenge.entity.user.Address;
import com.workintech.s19challenge.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AddressRepository extends JpaRepository<Address,Long> {




}
