package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired(required = true)
    private UserRepository useRepository;

    public List<User> findAll(){
        return useRepository.findAll();
    }

    public User getUserByCredentials(String email, String password){
        return useRepository.findByEmailAndPassword(email, password);
    }

    public User findById(int id){
        return useRepository.findById(id).orElse(null);
    }
}
