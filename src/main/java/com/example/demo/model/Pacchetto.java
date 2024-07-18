package com.example.demo.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Pacchetto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Nome richiesto")
	private String name;
	
	@NotNull(message = "Prezzo richiesto")
	@Min(value = 0, message = "Il prezzo deve essere maggiore di 0")
	private Double price;
	
	@NotBlank(message = "Descrizione richiesta")
	private String descrizione;
	
	@NotBlank(message = "Sistemazione richiesta")
	private String sistemazione;
	
	@NotNull(message = "Durata richiesta")
	@Min(value = 0, message = "Durata deve essere almeno 1 giorno")
	private Integer durata;
	
	private String immagine;

	@ManyToOne
	@JoinColumn(name = "destinazione_id")
	private Destinazione destinazione;
	
	@OneToMany(mappedBy = "pacchetto")
	private List<User> users;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getSistemazione() {
		return sistemazione;
	}

	public void setSistemazione(String sistemazione) {
		this.sistemazione = sistemazione;
	}

	public Integer getDurata() {
		return durata;
	}

	public void setDurata(Integer durata) {
		this.durata = durata;
	}

	public Destinazione getDestinazione() {
		return destinazione;
	}

	public void setDestinazione(Destinazione destinazione) {
		this.destinazione = destinazione;
	}

	public String getImmagine() {
		return immagine;
	}

	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}
