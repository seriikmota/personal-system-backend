package br.ueg.personalsystem.repository;

import br.ueg.personalsystem.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Boolean existsPatientByCpf(String cpf);
}
