package org.reactive.management.service.service;

import java.util.HashMap;
import java.util.Map;

import org.bouncycastle.crypto.Mac;
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

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PatientService {

	@Autowired
	private PatientRepository repository;

	@Autowired
	WebClient.Builder builder;

	private static final String ICU_URL = "http://icu-reactive-service";

	
	public Mono<Patient> save(Mono<Patient> patient) {
		return repository.saveAll(patient).next();
	}
	
	public Flux<Patient> getAll() {
		return repository.findAll();
	}

	public Mono<Patient> transfer(Mono<TransferDTO> transfer) {
		Mono<Patient> patient = transfer.flatMap(mapper -> repository.findById(mapper.getPatientId()).switchIfEmpty(Mono.error(new 	IllegalStateException("Paciente não existe.")))).map(mapper -> {
			builder.baseUrl(ICU_URL)
				.build()
				.post()
				.body(BodyInserters.fromValue(getBodyRequest(mapper.getId())))
				.retrieve()
				.bodyToMono(Object.class)
				.subscribe();
			return mapper;
		});

		// patient.subscribe(next -> {
		// 	try {
		// 		callHospitalService(next.getId(), );
		// 	} catch (TransferException e) {
		// 		// TODO Auto-generated catch block
		// 		e.printStackTrace();
		// 	}
		// });
		return patient;
		
		// return transfer.map(mapper -> repository.findById(mapper.getPatientId()).switchIfEmpty(null))
		// .handle((patient, sink) -> {
		// 	if (patient == null) {
		// 		sink.error(new Exception());
		// 	} else {
		// 			patient.doOnNext(mapper -> {
		// 				try {
		// 					callHospitalService(mapper.getId(), "");
		// 				} catch (TransferException e) {
		// 					// TODO Auto-generated catch block
		// 					e.printStackTrace();
		// 				}
		// 			});
			
		// 	}
		// });
		

	}

	private Map<String, Integer> getBodyRequest(Integer patientId) {
		Map<String, Integer> map = new HashMap<>();
		map.put("patientId", patientId);
		return map;
	}

	// private void callHospitalService(Integer patientId, String url) throws TransferException {
	// 	Map<String, Integer> map = new HashMap<>();
	// 	map.put("patientId", patientId);
	// 	ResponseEntity<String> response;
	// 	try {
	// 		response = restTemplate.postForEntity(url, map, String.class);
	// 	} catch (Exception e) {
	// 		throw new TransferException(HttpStatus.SERVICE_UNAVAILABLE,
	// 				"erro");
	// 	}
	// 	if (!HttpStatus.CREATED.equals(response.getStatusCode())) {
	// 		throw new TransferException(response.getStatusCode(),
	// 				"erro");
	// 	}
	// }
}
