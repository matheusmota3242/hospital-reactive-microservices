package org.reactive.management.service.service;

import java.util.HashMap;
import java.util.Map;

import org.core.reactive.model.dto.TransferDTO;
import org.reactive.management.service.model.Patient;
import org.reactive.management.service.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PatientService {

	@Autowired
	private PatientRepository repository;

	@Autowired
	WebClient.Builder builder;

	public Mono<Patient> save(Mono<Patient> patient) {
		return repository.saveAll(patient).next();
	}
	
	public Flux<Patient> getAll() {
		return repository.findAll();
	}

	public Mono<Object> transfer(Mono<TransferDTO> transfer, String destinationService) {
		
		return transfer.flatMap(transferMapping -> repository.findById(transferMapping.getPatientId()).switchIfEmpty(Mono.error(new 	ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não cadastrado.")))).flatMap(mapper -> 
			builder.baseUrl("http://".concat(destinationService).concat("/icu"))
				.build()
				.post()
				.body(getBodyRequest(mapper.getId()), Map.class)
				.exchangeToMono(response -> {
					if (response.statusCode().isError()) {
						return Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, String.format("Não foi possível chamar o serviço %s", destinationService)));
					}
					return response.bodyToMono(Object.class);
				})

		);

	}

	private Mono<Map<String, Integer>> getBodyRequest(Integer patientId) {
		return Mono.just(new HashMap<String, Integer>()).map(map -> {
			map.put("patientId", patientId);
			return map;
		});
	}


}
