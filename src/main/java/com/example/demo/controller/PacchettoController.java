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
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Pacchetto;
import com.example.demo.repository.PacchettoRepository;

import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/pacchetto")
@Validated
public class PacchettoController {

	@Autowired
	private PacchettoRepository pacchettoRepository;

	@GetMapping
	public List<Pacchetto> getAllPacchetto() {
		return pacchettoRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pacchetto> GetPacchetto(@PathVariable Long id) {
		Optional<Pacchetto> pacchetto = pacchettoRepository.findById(id);
		if (pacchetto.isPresent()) {
			return new ResponseEntity<>(pacchetto.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<Pacchetto>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public ResponseEntity<Pacchetto> createPacchetto(@Valid @RequestBody Pacchetto pacchetto) {
		Pacchetto savedPacchetto = pacchettoRepository.save(pacchetto);
		return new ResponseEntity<>(savedPacchetto, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Pacchetto> updatePacchetto(@PathVariable Long id,
			@Valid @RequestBody Pacchetto pacchettoUpdate) {
		Optional<Pacchetto> pacchetto = pacchettoRepository.findById(id);
		if (pacchetto.isPresent()) {
			Pacchetto trovato = pacchetto.get();
			trovato.setName(pacchettoUpdate.getName());
			trovato.setPrice(pacchettoUpdate.getPrice());
			trovato.setDescrizione(pacchettoUpdate.getDescrizione());
			trovato.setSistemazione(pacchettoUpdate.getSistemazione());
			trovato.setDurata(pacchettoUpdate.getDurata());
			trovato.setImmagine(pacchettoUpdate.getImmagine());
			return new ResponseEntity<>(pacchettoRepository.save(trovato), HttpStatus.OK);
		} else {
			return new ResponseEntity<Pacchetto>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteDestinazione(@PathVariable Long id) {
	    Optional<Pacchetto> destinazione = pacchettoRepository.findById(id);
	    if (destinazione.isPresent()) {
	        pacchettoRepository.delete(destinazione.get());
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);  
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
}
	

