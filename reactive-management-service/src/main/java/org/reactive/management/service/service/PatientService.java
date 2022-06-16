package org.reactive.management.service.service;

import org.reactive.management.service.model.Patient;
import org.reactive.management.service.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PatientService {

	@Autowired
	private PatientRepository repository;
	
	public Mono<Patient> save(Mono<Patient> patient) {
		return repository.saveAll(patient).next();
	}
	
	public Flux<Patient> getAll() {
		return repository.findAll();
	}
}
