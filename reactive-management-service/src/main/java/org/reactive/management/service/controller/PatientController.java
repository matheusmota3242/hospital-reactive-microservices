package org.reactive.management.service.controller;

import javax.validation.Valid;

import org.core.reactive.model.dto.TransferDTO;
import org.reactive.management.service.model.Patient;
import org.reactive.management.service.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/patients")
public class PatientController {

	@Autowired
	private PatientService service;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Patient> save(@Valid @RequestBody Mono<Patient> patient) {
		return service.save(patient);
	}
	
	@GetMapping
	public Flux<Patient> getAll() {
		return service.getAll();
	}

	@PostMapping("transfer/{destinationService}")
	public Mono<Object> transfer(@Valid @RequestBody Mono<TransferDTO> transfer, @PathVariable String destinationService) {
		
		return service.transfer(transfer, destinationService);
	}
}
