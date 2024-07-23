package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	// Trova un utente in base all'username
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	// Restituisce la lista di tutti gli utenti
	public List<User> findAll() {
		return userRepository.findAll();
	}

	public Optional<User> findById(Long userId) {
		return userRepository.findById(userId);
	}
}
