package br.ufrn.imd.mmgg.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import br.ufrn.imd.mmgg.model.ICUInterment;
import br.ufrn.imd.mmgg.repository.IntermentRepository;
import reactor.core.publisher.Mono;

@Service
public class InternmentService {
    
    @Autowired
    private IntermentRepository repository;
    
    public Mono<ICUInterment> save(Mono<ICUInterment> internment) {
        return repository.saveAll(internment).next();
    }

}
