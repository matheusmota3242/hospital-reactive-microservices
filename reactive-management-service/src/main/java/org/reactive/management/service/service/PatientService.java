package org.reactive.management.service.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.bouncycastle.crypto.Mac;
import org.core.reactive.enuns.TypeAction;
import org.core.reactive.model.dto.TransferDTO;
import org.reactive.management.service.exception.TransferException;
import org.reactive.management.service.model.Patient;
import org.reactive.management.service.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
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

	private static final String ICU_URL = "http://icu-reactive-service";
	private static final String INPATIENT_URL = "http://inpatient-unit-reactive-service";


	
	public Mono<Patient> save(Mono<Patient> patient) {
		return repository.saveAll(patient).next();
	}
	
	public Flux<Patient> getAll() {
		return repository.findAll();
	}

	// TODO pensar em solução para obter a url dinamicamente
	public Mono<Patient> transfer(Mono<TransferDTO> transfer) {
		Mono<Patient> patient = transfer.flatMap(transferMapping -> repository.findById(transferMapping.getPatientId()).switchIfEmpty(Mono.error(new 	ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não cadastrado.")))).map(mapper -> {
			builder.baseUrl(ICU_URL)
				.build()
				.post()
				.body(getBodyRequest(mapper.getId()), Map.class)
				.retrieve()
				.bodyToMono(Object.class)
				.subscribe();
			return mapper;
		});

		return patient;

	}

	private Mono<Map<String, Integer>> getBodyRequest(Integer patientId) {
		return Mono.just(new HashMap<String, Integer>()).map(map -> {
			map.put("patientId", patientId);
			return map;
		});
	}

	// private String getUrl(TypeAction typeAction) {
	// 	String url = StringUtils.EMPTY;
	// 	if (TypeAction.TO_ICU.equals(typeAction)) {
	// 		url = ICU_URL;
	// 	} else if (TypeAction.TO_INPATIENT_UNIT.equals(typeAction)) {
	// 		url = INPATIENT_URL;
	// 	}
	// 	return url;
	// }

}
