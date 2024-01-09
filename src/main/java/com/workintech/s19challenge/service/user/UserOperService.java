package com.workintech.s19challenge.service.user;

import com.workintech.s19challenge.entity.user.User;

import java.util.List;

public interface UserOperService {

    List<User> findAll();
    User findByID(long id);
    User save(User user);
    User delete(long id);

}
