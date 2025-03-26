package br.ueg.personalsystem.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AnamneseResponseDTO {
   private Long id;
   private PatientResponseDTO patient;
   private LocalDate anamnesisDate;
   private String mainComplaints;
   private String medicalHistory;
   private String observations;

   private Double weight;
   private Double height;
   private Double waistCircumference;
   private Double hipCircumference;
   private Double bodyFatPercentage;
   private Double muscleMass;

   private Double bodyMassIndex;
   private Double waistHipRatio;
}
