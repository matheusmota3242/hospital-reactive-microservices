package br.ufrn.imd.mmgg.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.ufrn.imd.mmgg.model.Internment;
import br.ufrn.imd.mmgg.service.InternmentService;
import reactor.core.publisher.Mono;

@RestController
public class InternmentController {
    
    @Autowired
    private InternmentService service;

    @PostMapping
    public Mono<Internment> save(@Valid @RequestBody Mono<Internment> internment) {
        return service.save(internment);
    } 
}
