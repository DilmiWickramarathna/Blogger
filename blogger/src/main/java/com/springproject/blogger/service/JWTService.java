package com.springproject.blogger.service;

import com.springproject.blogger.model.UserLogin;

public interface JWTService {
    String generateToken(String userName);
}
