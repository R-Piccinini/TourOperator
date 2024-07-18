package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Pacchetto;

@Repository
public interface PacchettoRepository extends JpaRepository<Pacchetto, Long> {
	
	 List<Pacchetto> findByNameAndDescrizione(String name,String password);

}
