package com.example.stocksystem.utill;


public class ClientNotCreatedException extends RuntimeException{
    public ClientNotCreatedException(String message){
        super(message);
    }
}
