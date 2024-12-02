package br.ueg.personalsystem.controller.impl;

import br.ueg.genericarchitecture.controller.impl.AbstractCrudController;
import br.ueg.personalsystem.controller.IPatientController;
import br.ueg.personalsystem.dto.list.PatientListDTO;
import br.ueg.personalsystem.dto.request.PatientRequestDTO;
import br.ueg.personalsystem.dto.response.PatientResponseDTO;
import br.ueg.personalsystem.entities.Patient;
import br.ueg.personalsystem.mapper.PatientMapper;
import br.ueg.personalsystem.service.impl.PatientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.version}/patient")
public class PatientController extends AbstractCrudController<PatientRequestDTO, PatientResponseDTO, PatientListDTO, Patient, PatientService, PatientMapper, Long>
        implements IPatientController {

    @GetMapping(path = "/search")
    @PreAuthorize("hasRole(#root.this.getRoleName('LISTALL'))")
    public ResponseEntity<Page<PatientListDTO>> search(@RequestParam(required = false) String name,
                                                       @RequestParam(required = false) String cpf,
                                                       Pageable pageable){
        Page<PatientListDTO> listDTO = service.search(name, cpf, pageable).map(obj -> mapper.toDTOList(obj));
        return ResponseEntity.ok(listDTO);
    }

}