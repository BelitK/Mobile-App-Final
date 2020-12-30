package com.example.myapplication.service;

import android.content.Context;

import com.example.myapplication.repository.UserRepository;

public class UserService {

    private UserRepository userRepository;

    public UserService(Context context) {
        this.userRepository = new UserRepository(context);
    }

    public boolean saveUser(String username,String password){
        return userRepository.saveUser(username,password);
    }

    public boolean checkUser(String username,String password){
        return userRepository.checkUser(username,password);
    }


}
