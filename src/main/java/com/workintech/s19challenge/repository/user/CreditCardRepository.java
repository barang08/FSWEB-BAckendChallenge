package com.workintech.s19challenge.repository.user;

import com.workintech.s19challenge.entity.user.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CreditCardRepository extends JpaRepository<CreditCard,Long> {

    @Query("SELECT c FROM CreditCard c WHERE c.nameAndSurname = :nameAndSurname")
    Optional<CreditCard> findByFullName(String nameAndSurname);
}
