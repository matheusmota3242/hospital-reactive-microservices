package br.ufrn.imd.mmgg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufrn.imd.mmgg.model.ICUInterment;
import br.ufrn.imd.mmgg.service.InternmentService;
import reactor.core.publisher.Mono;

@RestController
public class IntenrmentController {
    
    @Autowired
    private InternmentService service;

    @PostMapping
    public Mono<ICUInterment> save(Mono<ICUInterment> interment) throws Exception {
        return service.save(interment);
    }
}
