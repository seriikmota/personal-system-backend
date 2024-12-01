package br.ueg.personalsystem.controller;

import br.ueg.personalsystem.dto.list.PatientListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public interface IPatientController {

    ResponseEntity<Page<PatientListDTO>> search(@RequestParam(required = false) String name,
                                                @RequestParam(required = false) String cpf,
                                                Pageable pageable);

}
