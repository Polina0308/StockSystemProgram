package com.example.stocksystem.controller;

import com.example.stocksystem.entity.Client;
import com.example.stocksystem.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
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
}
