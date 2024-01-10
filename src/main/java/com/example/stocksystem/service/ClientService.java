package com.example.stocksystem.service;

import com.example.stocksystem.DAO.ClientRepository;
import com.example.stocksystem.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService
{
    @Autowired
    private ClientRepository clientRepository;

    public Client createClient(Client client){
        return clientRepository.save(client);
    }

    public Client getClient ( Integer clientId){
        return clientRepository.findById(clientId).orElse(null);
    }

    public Client updateClient(int clientId, Client updatedClientInfo) {
        Client existingClient = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        existingClient.setName(updatedClientInfo.getName());
        existingClient.setEmail(updatedClientInfo.getEmail());
        existingClient.setPhone_number(updatedClientInfo.getPhone_number());
        existingClient.setDateOfBirthday(updatedClientInfo.getDateOfBirthday());

        return clientRepository.save(existingClient);
    }

    public void deleteClient(int clientId) {
        Client existingClient = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        clientRepository.delete(existingClient);
    }
}
