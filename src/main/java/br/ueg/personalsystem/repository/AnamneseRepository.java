package br.ueg.personalsystem.repository;

import br.ueg.personalsystem.entities.Anamnese;
import br.ueg.personalsystem.dto.report.AnamneseCountByDayDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AnamneseRepository extends JpaRepository<Anamnese, Long> {
    @Query("SELECT a FROM Anamnese a WHERE " +
            "(COALESCE(:patientName, NULL) IS NULL OR LOWER(a.patient.name) LIKE LOWER(CONCAT('%', :patientName, '%'))) " +
            "AND (COALESCE(:startDate, NULL) IS NULL OR a.anamnesisDate >= :startDate) " +
            "AND (COALESCE(:endDate, NULL) IS NULL OR a.anamnesisDate <= :endDate)")
    Page<Anamnese> searchAnamnese(@Param("patientName") String patientName,
                                  @Param("startDate") LocalDate startDate,
                                  @Param("endDate") LocalDate endDate,
                                  Pageable pageable);

    @Query("""
    SELECT new br.ueg.personalsystem.dto.report.AnamneseCountByDayDTO(
        CAST(a.anamnesisDate AS string), COUNT(a))
    FROM Anamnese a
    GROUP BY a.anamnesisDate
    ORDER BY a.anamnesisDate DESC
""")
    List<AnamneseCountByDayDTO> findAnamneseCountByDay();
}
