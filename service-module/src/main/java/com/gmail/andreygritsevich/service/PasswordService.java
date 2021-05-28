package com.gmail.andreygritsevich.service;

public interface PasswordService {

    String getNewPassword();

    void sendPasswordToEmail(String password, String email);

}
