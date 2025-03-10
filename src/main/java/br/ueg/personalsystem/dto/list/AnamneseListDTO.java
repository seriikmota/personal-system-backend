package br.ueg.personalsystem.dto.list;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AnamneseListDTO {
   private Long id;
   private PatientListDTO patientName;
   private LocalDate anamnesisDate;
}
