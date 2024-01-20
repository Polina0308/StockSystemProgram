package com.example.stocksystem.message;

import com.example.stocksystem.entity.Client;

import java.util.List;

public class ClientSearchResponse {
    private String message;
    private List<Client> clients;


    public ClientSearchResponse(String message, List<Client> clients) {
        this.message = message;
        this.clients = clients;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }
}
