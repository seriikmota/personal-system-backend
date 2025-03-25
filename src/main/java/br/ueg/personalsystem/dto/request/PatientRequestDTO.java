package br.ueg.personalsystem.dto.request;

import br.ueg.personalsystem.base.annotation.MandatoryField;
import br.ueg.personalsystem.annotation.CPFValidate;
import br.ueg.personalsystem.annotation.EmailValidate;
import br.ueg.personalsystem.annotation.NumberPhoneValidate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PatientRequestDTO {

    private Long id;

    @MandatoryField(name = "Nome")
    private String name;

    @MandatoryField(name = "Cpf")
    @CPFValidate(name = "Cpf")
    private String cpf;

    @MandatoryField(name = "Endereço")
    private AddressRequestDTO address;

    @MandatoryField(name = "Data de aniversário")
    private LocalDate birthDate;

    @MandatoryField(name = "Sexo")
    private String gender;

    @MandatoryField(name = "Estado Civil")
    private String maritalStatus;

    @MandatoryField(name = "Email")
    @EmailValidate(name = "Email")
    private String email;

    @MandatoryField(name = "Número de telefone")
    @NumberPhoneValidate(name = "Número de telefone")
    private String phoneNumber;

    @MandatoryField(name = "Número de emergência")
    @NumberPhoneValidate(name = "Número de emergência")
    private String emergencyNumber;

    @MandatoryField(name = "Profissão")
    private String profession;

    private String healthPlan;

    @MandatoryField(name = "Valor por hora/aula")
    private Double valueForHour;

    private Integer classesPerMonth;

    @MandatoryField(name = "Status")
    private Boolean enabled;
}
