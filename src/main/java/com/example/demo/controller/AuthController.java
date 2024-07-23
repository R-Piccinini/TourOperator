package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.exception.UnauthorizedException;
import com.example.demo.model.AuthRequest;
import com.example.demo.model.AuthResponse;
import com.example.demo.model.User;
import com.example.demo.service.TokenService;
import com.example.demo.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    // Endpoint per il login
    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest authRequest) {
    	// Trova l'utente nel database in base all'username
    	User user = userService.findByUsername(authRequest.getUsername());
    	// Verifica se l'utente esiste e se la password è corretta
    	if (user != null && user.getPassword().equals(authRequest.getPassword())) {
    		// Genera un token e lo salva nel database
    		String token = tokenService.createToken(user.getId()).getToken();
            return new AuthResponse(token);
        } else {
        	 // Se le credenziali sono errate, lancia un'eccezione di non autorizzato
            throw new UnauthorizedException();
        }
    }

    // Endpoint per il logout
    @PostMapping("/logout")
    public void logout(@RequestHeader("Authorization") String token) {
    	System.out.println("my token:" + token);
        // Elimina il token dal database per effettuare il logout
    	tokenService.deleteByToken(token);
    }
}