package br.ueg.personalsystem.repository;

import br.ueg.personalsystem.dto.report.*;
import br.ueg.personalsystem.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Long countByEnabled(Boolean enabled);
    Long countByGender(String gender);

    Boolean existsPatientByCpf(String cpf);

    @Query("SELECT p FROM Patient p WHERE (:name IS NULL OR LOWER(p.name) LIKE %:name%) AND (:cpf IS NULL OR p.cpf LIKE %:cpf%)")
    Page<Patient> searchByNameLikeAndCpfLike(@Param("name") String name, @Param("cpf") String cpf, Pageable pageable);

    @Query("""
        SELECT p FROM Patient p
        WHERE (:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%')))
        AND (:cpf IS NULL OR p.cpf LIKE CONCAT('%', :cpf, '%'))
    """)
    Page<Patient> searchByNameAndCpf(@Param("name") String name, @Param("cpf") String cpf, Pageable pageable);

    @Query("""
        SELECT new br.ueg.personalsystem.dto.report.BirthdayDTO(p.id, p.name, p.birthDate)
        FROM Patient p
        WHERE FUNCTION('MONTH', p.birthDate) = FUNCTION('MONTH', CURRENT_DATE)
        AND FUNCTION('DAY', p.birthDate) = FUNCTION('DAY', CURRENT_DATE)
    """)
    List<BirthdayDTO> findDailyBirthdays();

    @Query("""
        SELECT new br.ueg.personalsystem.dto.report.BirthdayDTO(p.id, p.name, p.birthDate)
        FROM Patient p
        WHERE FUNCTION('MONTH', p.birthDate) = FUNCTION('MONTH', CURRENT_DATE)
    """)
    List<BirthdayDTO> findMonthlyBirthdays();

    @Query("""
        SELECT COUNT(p) FROM Patient p JOIN p.anamneses a
    """)
    Long countClientsWithAnamnese();

    @Query("""
        SELECT COUNT(p) FROM Patient p WHERE p.id NOT IN (SELECT a.patient.id FROM Anamnese a)
    """)
    Long countClientsWithoutAnamnese();

    @Query("""
        SELECT new br.ueg.personalsystem.dto.report.ClientsBySubscriptionDTO(p.profession, COUNT(p))
        FROM Patient p GROUP BY p.profession
    """)
    List<ClientsBySubscriptionDTO> findClientsBySubscription();

    @Query("""
        SELECT new br.ueg.personalsystem.dto.report.ClientsByAgeDTO(
            CAST(YEAR(CURRENT_DATE) - YEAR(p.birthDate) AS string), COUNT(p))
        FROM Patient p GROUP BY YEAR(p.birthDate)
    """)
    List<ClientsByAgeDTO> findClientsByAge();

    @Query("""
        SELECT new br.ueg.personalsystem.dto.report.ClientsByCityDTO(a.city, COUNT(p))
        FROM Patient p JOIN p.address a GROUP BY a.city
    """)
    List<ClientsByCityDTO> findClientsByCity();

    @Query("""
    SELECT new br.ueg.personalsystem.dto.report.ClientGrowthDTO(CAST(p.createdAt AS LocalDate), COUNT(p))
    FROM Patient p 
    JOIN p.anamneses a 
    GROUP BY CAST(p.createdAt AS LocalDate)
""")
    List<ClientGrowthDTO> findClientGrowthWithAnamnese();

    @Query("""
        SELECT new br.ueg.personalsystem.dto.report.ClientGrowthDTO(
            CAST(p.createdAt AS LocalDate), COUNT(p))
        FROM Patient p WHERE p.enabled = true GROUP BY CAST(p.createdAt AS LocalDate)
    """)
    List<ClientGrowthDTO> findActiveClientGrowth();
}