package br.ueg.personalsystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PatientResponseDTO {
    private Long id;
    private String name;
    private String cpf;
    private LocalDate birthDate;
    private String gender;
    private String maritalStatus;
    private String email;
    private String phoneNumber;
    private String emergencyNumber;
    private String profession;
    private Double valueForHour;
    private LocalDateTime createdAt;
}
