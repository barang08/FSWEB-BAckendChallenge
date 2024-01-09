package com.workintech.s19challenge.service.user;

import com.workintech.s19challenge.entity.user.User;
import com.workintech.s19challenge.exception.GlobalException;
import com.workintech.s19challenge.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserOperServiceImpl implements UserOperService{

    private final UserRepository userRepository;

    @Autowired
    public UserOperServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByID(long id) {
        Optional<User> findUser = userRepository.findById(id);
        if(findUser.isPresent()){
            return findUser.get();
        }else{
            throw new GlobalException("User is not found with given id"+id, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public User save(User user) {
        Optional<User> saveUser = userRepository.findByEmail(user.getEmail());
        if(saveUser.isPresent()){
            User foundedUSer= userRepository.findByEmail(user.getEmail()).get();
            throw new GlobalException("User is already exist", HttpStatus.BAD_REQUEST);
        }else{
            return userRepository.save(user);
        }
    }

    @Override
    public User delete(long id) {
        User user = findByID(id);
        if(user != null){
            userRepository.delete(user);
            return user;
        }else{
            throw new GlobalException("User is not define with given id:"+id,HttpStatus.NOT_FOUND);
        }
    }
}
