package br.ueg.personalsystem.dto.report;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientProfitEstimateDTO {
    private Long patientId;
    private String patientName;
    private Double profitEstimate;
    private Integer totalClasses;
}