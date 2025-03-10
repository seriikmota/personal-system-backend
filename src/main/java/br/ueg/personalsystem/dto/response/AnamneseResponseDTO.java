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
   private Long patientId;
   private LocalDate birthDate;
   private LocalDate anamnesisDate;
   private String mainComplaints;
   private String medicalHistory;
   private String observations;
}
