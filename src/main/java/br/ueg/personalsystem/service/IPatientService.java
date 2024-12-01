package br.ueg.personalsystem.service;

import br.ueg.genericarchitecture.service.IAbstractService;
import br.ueg.personalsystem.dto.request.PatientRequestDTO;
import br.ueg.personalsystem.entities.Patient;

public interface IPatientService extends IAbstractService<PatientRequestDTO, Patient, Long> {
}
