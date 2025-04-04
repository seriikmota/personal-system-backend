package br.ueg.personalsystem.dto.request;


import br.ueg.personalsystem.base.annotation.MandatoryField;
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

    @MandatoryField(name = "Paciente")
    private PatientRequestDTO patient;

    @MandatoryField(name = "Data da Anamnese")
    private LocalDate anamnesisDate;

    @MandatoryField(name = "Queixas Principais")
    private String mainComplaints;

    @MandatoryField(name = "Histórico Médico")
    private String medicalHistory;

    private String observations;

    @MandatoryField(name = "Peso")
    private Double weight;

    @MandatoryField(name = "Altura")
    private Double height;

    @MandatoryField(name = "Circunferência da Cintura")
    private Double waistCircumference;

    @MandatoryField(name = "Circunferência do Quadril")
    private Double hipCircumference;

    @MandatoryField(name = "Percentual de Gordura Corporal")
    private Double bodyFatPercentage;

    @MandatoryField(name = "Massa Muscular")
    private Double muscleMass;

    @MandatoryField(name = "Índice de Massa Corporal (IMC)")
    private Double bodyMassIndex;

    @MandatoryField(name = "Relação Cintura/Quadril")
    private Double waistHipRatio;
}
