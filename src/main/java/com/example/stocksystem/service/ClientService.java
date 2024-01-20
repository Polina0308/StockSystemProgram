package com.example.stocksystem.service;

import com.example.stocksystem.DAO.ClientRepository;
import com.example.stocksystem.entity.Client;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService
{
    @Autowired
    private ClientRepository clientRepository;

    public Client createClient(@Valid Client client){
        return clientRepository.save(client);
    }

    public Client getClient ( Integer clientId){
        return clientRepository.findById(clientId).orElseThrow(() -> new RuntimeException("Клиент не найден"));
    }

    public Client updateClient(int clientId, Client updatedClientInfo) {
        Client existingClient = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Клиент не найден"));

        existingClient.setName(updatedClientInfo.getName());
        existingClient.setEmail(updatedClientInfo.getEmail());
        existingClient.setPhone_number(updatedClientInfo.getPhone_number());
        existingClient.setDateOfBirthday(updatedClientInfo.getDateOfBirthday());

        return clientRepository.save(existingClient);
    }

    public void deleteClient(int clientId) {
        Client existingClient = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Клиент не найден"));

        clientRepository.delete(existingClient);
    }

    public List<Client> findClientByEmail(String email) {
        List<Client> clients = clientRepository.findByEmail(email);

       return clients;
    }


    public List<Client> findClientsByName(String name) {
        List<Client> clients = clientRepository.findByName(name);

        return clients;

    }

    public List<Client> findClientsByPhoneNumber(String phone_number) {
        List<Client> clients = clientRepository.findByPhone_number(phone_number);

        return clients;
    }


}
