package br.ueg.personalsystem.service;

import br.ueg.personalsystem.base.service.IAbstractService;
import br.ueg.personalsystem.dto.request.PatientRequestDTO;
import br.ueg.personalsystem.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPatientService extends IAbstractService<PatientRequestDTO, Patient, Long> {
    Page<Patient> search(String name, String cpf, Pageable pageable);
}
