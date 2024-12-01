package br.ueg.personalsystem.dto.list;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PatientListDTO {
    private Long id;
    private String name;
    private String cpf;
    private String email;
    private String phoneNumber;
    private Boolean enabled;
}
