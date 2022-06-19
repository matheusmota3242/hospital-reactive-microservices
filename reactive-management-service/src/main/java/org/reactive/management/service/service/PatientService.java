package org.reactive.management.service.service;

import java.util.HashMap;
import java.util.Map;

import org.bouncycastle.crypto.Mac;
import org.core.reactive.model.dto.TransferDTO;
import org.reactive.management.service.exception.TransferException;
import org.reactive.management.service.model.Patient;
import org.reactive.management.service.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PatientService {

	@Autowired
	private PatientRepository repository;

	@Autowired
	private RestTemplate restTemplate;
	
	public Mono<Patient> save(Mono<Patient> patient) {
		return repository.saveAll(patient).next();
	}
	
	public Flux<Patient> getAll() {
		return repository.findAll();
	}

	public Mono<Patient> transfer(Mono<TransferDTO> transfer) {
		Mono<Patient> patient = transfer.flatMap(mapper -> repository.findById(mapper.getPatientId()).switchIfEmpty(Mono.just(new Patient())));

		patient.subscribe(next -> {
			try {
				callHospitalService(next.getId(), "http://management-service");
			} catch (TransferException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
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

	private void callHospitalService(Integer patientId, String url) throws TransferException {
		Map<String, Integer> map = new HashMap<>();
		map.put("patientId", patientId);
		ResponseEntity<String> response;
		try {
			response = restTemplate.postForEntity(url, map, String.class);
		} catch (Exception e) {
			throw new TransferException(HttpStatus.SERVICE_UNAVAILABLE,
					"erro");
		}
		if (!HttpStatus.CREATED.equals(response.getStatusCode())) {
			throw new TransferException(response.getStatusCode(),
					"erro");
		}
	}
}
