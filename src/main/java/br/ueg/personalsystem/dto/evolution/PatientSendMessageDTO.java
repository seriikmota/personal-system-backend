package br.ueg.personalsystem.dto.evolution;

import br.ueg.personalsystem.entities.Patient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PatientSendMessageDTO {
    private List<Patient> patients;
    private String message;
}
