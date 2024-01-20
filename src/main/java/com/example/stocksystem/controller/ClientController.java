package com.example.stocksystem.controller;

import com.example.stocksystem.entity.Client;
import com.example.stocksystem.message.ClientSearchResponse;
import com.example.stocksystem.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<Client> createClient(@Valid @RequestBody Client client) {
        Client createdClient = clientService.createClient(client);
        return new ResponseEntity<>(createdClient, HttpStatus.CREATED);
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<Client> getClient(@PathVariable int clientId) {
        Client client = clientService.getClient(clientId);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<Client> updateClient(@PathVariable int clientId, @RequestBody Client updatedClientInfo) {
        Client updatedClient = clientService.updateClient(clientId, updatedClientInfo);
        return ResponseEntity.ok(updatedClient);
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<Void> deleteClient(@PathVariable int clientId) {
        clientService.deleteClient(clientId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/searchByEmail/{email}")
    public ResponseEntity<?> searchClientsByEmail(@PathVariable String email) {
        List<Client> clients = clientService.findClientByEmail(email);

        if (clients.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Клиент не найден");
        }

        return ResponseEntity.ok(clients);
    }



    @GetMapping("/searchByName/{name}")
    public ResponseEntity<?> searchClientsByName(@PathVariable String name) {
        List<Client> clients = clientService.findClientsByName(name);

        if (clients.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Клиент не найден");
        }

        return ResponseEntity.ok(clients);
    }
    @GetMapping("/searchByPhoneNumber/{phone_number}")
    public ResponseEntity<?> searchClientsByPhoneNumber(@PathVariable String phone_number) {
        List<Client> clients = clientService.findClientsByPhoneNumber(phone_number);

        if (clients.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Клиент не найден");
        }

        return ResponseEntity.ok(clients);
    }


}
