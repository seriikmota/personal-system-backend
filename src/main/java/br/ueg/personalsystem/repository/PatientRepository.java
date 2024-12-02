package br.ueg.personalsystem.repository;

import br.ueg.personalsystem.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Boolean existsPatientByCpf(String cpf);
    @Query("SELECT p FROM Patient p WHERE (:name IS NULL OR LOWER(p.name) LIKE %:name%) AND (:cpf IS NULL OR p.cpf LIKE %:cpf%)")
    Page<Patient> searchByNameLikeAndCpfLike(@Param("name") String name, @Param("cpf") String cpf, Pageable pageable);
}
