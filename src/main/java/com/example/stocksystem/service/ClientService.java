package com.example.stocksystem.service;

import com.example.stocksystem.DAO.ClientRepository;
import com.example.stocksystem.entity.Client;
import com.example.stocksystem.utill.ClientNotDeletedException;
import com.example.stocksystem.utill.ClientNotFoundException;
import com.example.stocksystem.utill.ClientNotUpdatedException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService
{
    @Autowired
    private ClientRepository clientRepository;

    @Transactional
    public Client createClient(@Valid Client client){
        return clientRepository.save(client);
    }



    public Client updateClient(int clientId, Client updatedClientInfo) {
        Client existingClient = clientRepository.findById(clientId)
                .orElseThrow(ClientNotUpdatedException::new);

        existingClient.setName(updatedClientInfo.getName());
        existingClient.setEmail(updatedClientInfo.getEmail());
        existingClient.setPhone_number(updatedClientInfo.getPhone_number());
        existingClient.setDateOfBirthday(updatedClientInfo.getDateOfBirthday());

        return clientRepository.save(existingClient);
    }

    public void deleteClient(int clientId) {
        Client existingClient = clientRepository.findById(clientId)
                .orElseThrow(ClientNotDeletedException::new);

        clientRepository.delete(existingClient);
    }

    public Client getClient ( Integer clientId){
        Optional <Client> clients = clientRepository.findById(clientId);
        return clients.orElseThrow(ClientNotFoundException::new);
    }

    public Client findClientByEmail(String email) {
        Optional <Client> clients = clientRepository.findByEmail(email);

       return clients.orElseThrow(ClientNotFoundException::new);
    }

    public List<Client> findClientsByName(String name) {
        Optional<Client> clients = clientRepository.findByName(name);

        return (List<Client>) clients.orElseThrow(ClientNotFoundException::new);

    }

    public Client findClientsByPhoneNumber(String phone_number) {
        Optional <Client> clients = clientRepository.findByPhone_number(phone_number);
        return clients.orElseThrow(ClientNotFoundException::new);
    }


}
