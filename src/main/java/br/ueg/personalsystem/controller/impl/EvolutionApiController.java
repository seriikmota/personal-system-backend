package br.ueg.personalsystem.controller.impl;

import br.ueg.personalsystem.controller.IEvolutionApiController;
import br.ueg.personalsystem.service.IEvolutionApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.version}/evolution")
public class EvolutionApiController implements IEvolutionApiController {

    @Autowired
    protected IEvolutionApiService service;

    @GetMapping
    public ResponseEntity<Object> apiInformation(){
        return ResponseEntity.status(HttpStatus.OK).body(service.apiInformation());
    }

    @PostMapping(path = "/instance/connect")
    @Transactional
    @PreAuthorize(value = "hasRole('ROLE_USER_CREATE')")
    public ResponseEntity<Object> connectInstance(){
        return ResponseEntity.status(HttpStatus.OK).body(service.connectInstance());
    }

    @DeleteMapping(path = "/instance/logout")
    @Transactional
    @PreAuthorize(value = "hasRole('ROLE_USER_CREATE')")
    public ResponseEntity<Object> logoutInstance(){
        return ResponseEntity.status(HttpStatus.OK).body(service.logoutInstance());
    }
}
