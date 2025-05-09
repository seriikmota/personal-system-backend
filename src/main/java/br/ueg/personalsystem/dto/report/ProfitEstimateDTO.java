package br.ueg.personalsystem.dto.report;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfitEstimateDTO {
    private Double totalProfitEstimate;
    private Long totalClasses;
    private List<PatientProfitEstimateDTO> patientProfits;
}