package com.workintech.s19challenge.repository.user;

import com.workintech.s19challenge.entity.user.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoleRepositoryTest {

    private RoleRepository roleRepository;


    @Autowired
    public RoleRepositoryTest(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Test
    @DisplayName("Can find role by authority")
    void findByAuthority(){
        String authority ="ADMIN";

        Role role = new Role();
        role.setAuthority(authority);
        roleRepository.save(role);

        Optional<Role> foundRole = roleRepository.findByAuthority(authority);

        assertTrue(foundRole.isPresent());

        /*Role realRole = foundRole.get();
        assertEquals(authority,realRole.getAuthority());*/



    }





}