package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/user")
@Validated
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping
	public List<User> getAllUser() {
		return userRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> GetUser(@PathVariable Long id) {
		Optional<User> utente = userRepository.findById(id);
		if (utente.isPresent()) {
			return new ResponseEntity<>(utente.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/login")
	public ResponseEntity<User> Login(@RequestParam String username,@RequestParam String password ) {
		Optional<User> utente = userRepository.findByUsernameAndPassword(username, password);
		if (utente.isPresent()) {
			return new ResponseEntity<>(utente.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping
	public ResponseEntity<User> createUser(@Valid @RequestBody User utente) {
		User savedUser = userRepository.save(utente);
		return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id,
			@Valid @RequestBody User userUpdate) {
		Optional<User> utente = userRepository.findById(id);
		if (utente.isPresent()) {
			User trovato = utente.get();
			trovato.setUsername(userUpdate.getUsername());
			trovato.setPassword(userUpdate.getPassword());
			trovato.setEmail(userUpdate.getEmail());
			trovato.setNome(userUpdate.getNome());
			trovato.setCognome(userUpdate.getCognome());
			trovato.setIndirizzo(userUpdate.getIndirizzo());
			trovato.setDataNascita(userUpdate.getDataNascita());
			return new ResponseEntity<>(userRepository.save(trovato), HttpStatus.OK);
		} else {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
	    Optional<User> utente = userRepository.findById(id);
	    if (utente.isPresent()) {
	        userRepository.delete(utente.get());
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);  
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
}
