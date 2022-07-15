package br.ufrn.imd.mmgg.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import br.ufrn.imd.mmgg.model.Internment;

@Repository
public interface InternmentRepository extends R2dbcRepository<Internment, Integer> {
    
}
