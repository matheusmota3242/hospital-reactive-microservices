package br.ufrn.imd.mmgg.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import br.ufrn.imd.mmgg.model.ICUInterment;
import reactor.core.publisher.Mono;

@Repository
public interface IntermentRepository extends R2dbcRepository<ICUInterment, Integer> {
 
}
