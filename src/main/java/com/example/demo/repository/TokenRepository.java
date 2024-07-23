package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Token;

public interface TokenRepository extends JpaRepository<Token, Long> {
	
	Token findByToken(String token);

	void deleteByToken(String token);
}
