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
   private PatientListDTO patient; // Usar DTO do paciente para evitar expor dados desnecessários
   private LocalDate anamnesisDate;
   private Double weight; // Peso
   private Double height; // Altura
   private Double bodyMassIndex; // IMC (pode ser calculado ou persistido)
}
