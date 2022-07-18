package br.ufrn.imd.mmgg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufrn.imd.mmgg.model.Internment;
import br.ufrn.imd.mmgg.repository.InternmentRepository;
import reactor.core.publisher.Mono;

@Service
public class InternmentService {

    @Autowired
    private InternmentRepository repository;
    
    public Mono<Internment> save(Mono<Internment> internment) {
        return internment.doOnNext(mapper -> {
            mapper.setStatus(true);
            repository.save(mapper);
        });
    } 

}