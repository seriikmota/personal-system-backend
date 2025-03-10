package br.ueg.personalsystem.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AnamneseRequestDTO {
    private PatientRequestAnamneseDTO patient;
    private  LocalDate birthDate;
    private LocalDate anamnesisDate;
    private String mainComplaints;
    private String medicalHistory;
    private String observations;
}
