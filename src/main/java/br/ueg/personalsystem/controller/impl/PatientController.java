package br.ueg.personalsystem.controller.impl;

import br.ueg.genericarchitecture.controller.impl.AbstractCrudController;
import br.ueg.personalsystem.controller.IPatientController;
import br.ueg.personalsystem.dto.list.PatientListDTO;
import br.ueg.personalsystem.dto.request.PatientRequestDTO;
import br.ueg.personalsystem.dto.response.PatientResponseDTO;
import br.ueg.personalsystem.entities.Patient;
import br.ueg.personalsystem.mapper.PatientMapper;
import br.ueg.personalsystem.service.impl.PatientService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.version}/patient")
public class PatientController extends AbstractCrudController<PatientRequestDTO, PatientResponseDTO, PatientListDTO, Patient, PatientService, PatientMapper, Long>
        implements IPatientController {
}