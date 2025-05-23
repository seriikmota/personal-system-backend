package br.ueg.personalsystem.controller.impl;

import br.ueg.personalsystem.controller.IEvolutionApiController;
import br.ueg.personalsystem.dto.evolution.ConnectInstanceResponseDTO;
import br.ueg.personalsystem.dto.evolution.ConnectionStatusDTO;
import br.ueg.personalsystem.dto.evolution.PatientSendMessageDTO;
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
    @PreAuthorize(value = "hasRole('ROLE_EVOLUTION_STATUS')")
    public ResponseEntity<Object> apiInformation(){
        return ResponseEntity.status(HttpStatus.OK).body(service.apiInformation());
    }

    @PostMapping(path = "/instance/create")
    @Transactional
    @PreAuthorize(value = "hasRole('ROLE_EVOLUTION_CREATE')")
    public ResponseEntity<String> createInstance(@RequestParam(value = "userId") Long userId, @RequestParam(value = "instanceName") String instanceName){
        service.createInstance(userId, instanceName);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping(path = "/instance/delete")
    @Transactional
    @PreAuthorize(value = "hasRole('ROLE_EVOLUTION_DELETE')")
    public ResponseEntity<String> deleteInstance(@RequestParam(value = "userId") Long userId) {
        service.deleteInstance(userId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping(path = "/instance/connect")
    @PreAuthorize(value = "hasRole('ROLE_EVOLUTION_CONNECT')")
    public ResponseEntity<ConnectInstanceResponseDTO> connectInstance() {
        ConnectInstanceResponseDTO responseDTO = service.connectInstance();
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @DeleteMapping(path = "/instance/logout")
    @PreAuthorize(value = "hasRole('ROLE_EVOLUTION_LOGOUT')")
    public ResponseEntity<String> logoutInstance() {
        service.logoutInstance();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping(path = "/instance/status")
    @PreAuthorize(value = "hasRole('ROLE_EVOLUTION_STATUS')")
    public ResponseEntity<ConnectionStatusDTO> statusInstance() {
        ConnectionStatusDTO responseDTO = service.connectionStatus();
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @PostMapping("/sendMessage")
    @PreAuthorize(value = "hasRole('ROLE_EVOLUTION_SEND_MESSAGE')")
    public ResponseEntity<?> sendMessage(@RequestBody PatientSendMessageDTO dto) {
        service.sendMessages(dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/checkIsWhatsApp")
    public ResponseEntity<Boolean> checkIsWhatsApp(@RequestParam String number) {
        Boolean isWhatsApp = service.checkIsWhatsApp(number);
        return ResponseEntity.ok(isWhatsApp);
    }
}
