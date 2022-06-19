package br.ufrn.imd.mmgg.service;

import java.time.LocalDateTime;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import br.ufrn.imd.mmgg.model.ICUInterment;
import br.ufrn.imd.mmgg.repository.IntermentRepository;
import reactor.core.publisher.Mono;

@Service
public class InternmentService {
    
    @Autowired
    private IntermentRepository repository;
    
    public Mono<ICUInterment> save(@Valid @RequestBody Mono<ICUInterment>       monoInterment) {
        return monoInterment
            .doOnNext(internment -> internment.setDateTime(LocalDateTime.now())).doOnNext(internment -> repository.save(internment))
            .handle((internment, sink) -> {
                if (null == internment.getId())
                    sink.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao tentar persistir internação."));
            });
    }

}
