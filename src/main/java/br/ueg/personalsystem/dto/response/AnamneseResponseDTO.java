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
   private Long patientId; // ID do paciente
   private LocalDate anamnesisDate;
   private String mainComplaints;
   private String medicalHistory;
   private String observations;

   // Campos de avaliação antropométrica
   private Double weight; // Peso em kg
   private Double height; // Altura em metros
   private Double waistCircumference; // Circunferência da cintura em cm
   private Double hipCircumference; // Circunferência do quadril em cm
   private Double bodyFatPercentage; // Percentual de gordura corporal
   private Double muscleMass; // Massa muscular em kg

   // Campos calculados
   private Double bodyMassIndex; // IMC (calculado ou persistido)
   private Double waistHipRatio; // Relação cintura/quadril (calculado ou persistido)
}
