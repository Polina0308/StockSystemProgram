package com.example.stocksystem.controller;

import com.example.stocksystem.entity.Client;
import com.example.stocksystem.service.ClientService;
import com.example.stocksystem.utill.*;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/clients")
public class ClientController {
    @Autowired
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody @Valid Client client, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append(";");
            }
            throw new ClientNotCreatedException(errorMsg.toString());
        }
        clientService.createClient(client);
        return ResponseEntity.ok(client);


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


    @GetMapping("/{clientId}")
    public Client getClient(@PathVariable int clientId) {
        return clientService.getClient(clientId);
    }

    @GetMapping("/searchByEmail/{email}")
    public Client searchClientsByEmail(@PathVariable String email) {
        return clientService.findClientByEmail(email);
    }

    @GetMapping("/searchByName/{name}")
    public List<Client> searchClientsByName(@PathVariable String name) {
        return clientService.findClientsByName(name);
    }

    @GetMapping("/searchByPhoneNumber/{phone_number}")
    public Client searchClientsByPhoneNumber(@PathVariable String phone_number) {
        return clientService.findClientsByPhoneNumber(phone_number);
    }


    @ExceptionHandler
    private ResponseEntity<ClientErrorResponse> handleException(ClientNotFoundException e) {
        ClientErrorResponse response = new ClientErrorResponse(
                "Клиент не найден",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<ClientErrorResponse> handleDeleteException(ClientNotDeletedException e) {
        ClientErrorResponse response = new ClientErrorResponse(
                "Не удалось удалить клиента",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<ClientErrorResponse> handleUpdateException(ClientNotUpdatedException e) {
        ClientErrorResponse response = new ClientErrorResponse(
                "Не удалось обновить данные о клиенте",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ClientErrorResponse> handleCreatedException(ClientNotCreatedException e) {
        ClientErrorResponse response = new ClientErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }



    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> handleDataIntegrityViolation(DataIntegrityViolationException ex) {

        String message = ex.getMostSpecificCause().getMessage();
        String errorMessage = "Неизвестная ошибка";

        if (message.contains("client_phonenumber_unq")) {
            errorMessage = "Номер телефона уже используется";
        } else if (message.contains("client_email_unq")) {
            errorMessage = "Email уже используется";
        }

        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("message", errorMessage);
        errorResponse.put("timestamp", System.currentTimeMillis());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}