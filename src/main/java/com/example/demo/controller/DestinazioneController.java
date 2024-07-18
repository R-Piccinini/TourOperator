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

import com.example.demo.model.Destinazione;
import com.example.demo.repository.DestinazioneRepository;

import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/destinazione")
@Validated
public class DestinazioneController {

	@Autowired
	private DestinazioneRepository destinazioneRepository;

	@GetMapping
	public List<Destinazione> getAllDestinazione() {
		return destinazioneRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Destinazione> GetDestinazione(@PathVariable Long id) {
		Optional<Destinazione> destinazione = destinazioneRepository.findById(id);
		if (destinazione.isPresent()) {
			return new ResponseEntity<>(destinazione.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<Destinazione>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public ResponseEntity<Destinazione> createDestinazione(@Valid @RequestBody Destinazione destinazione) {
		Destinazione savedDestinazione = destinazioneRepository.save(destinazione);
		return new ResponseEntity<>(savedDestinazione, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Destinazione> updateDestinazione(@PathVariable Long id,
			@Valid @RequestBody Destinazione destinazioneUpdate) {
		Optional<Destinazione> destinazione = destinazioneRepository.findById(id);
		if (destinazione.isPresent()) {
			Destinazione trovato = destinazione.get();
			trovato.setName(destinazioneUpdate.getName());
			trovato.setDescrizione(destinazioneUpdate.getDescrizione());
			return new ResponseEntity<>(destinazioneRepository.save(trovato), HttpStatus.OK);
		} else {
			return new ResponseEntity<Destinazione>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteDestinazione(@PathVariable Long id) {
	    Optional<Destinazione> destinazione = destinazioneRepository.findById(id);
	    if (destinazione.isPresent()) {
	        destinazioneRepository.delete(destinazione.get());
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);  
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
}