package org.reactive.management.service.repository;

import org.reactive.management.service.model.Patient;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends R2dbcRepository<Patient, Integer> {

}
