package br.ueg.personalsystem.dto.request;


import br.ueg.genericarchitecture.annotation.MandatoryField;
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

    private String observations; // Observações não são obrigatórias

    // Campos de avaliação antropométrica
    @MandatoryField(name = "Peso")
    private Double weight; // Peso em kg

    @MandatoryField(name = "Altura")
    private Double height; // Altura em metros

    @MandatoryField(name = "Circunferência da Cintura")
    private Double waistCircumference; // Circunferência da cintura em cm

    @MandatoryField(name = "Circunferência do Quadril")
    private Double hipCircumference; // Circunferência do quadril em cm

    @MandatoryField(name = "Percentual de Gordura Corporal")
    private Double bodyFatPercentage; // Percentual de gordura corporal

    @MandatoryField(name = "Massa Muscular")
    private Double muscleMass; // Massa muscular em kg

    // Campos calculados
    @MandatoryField(name = "Índice de Massa Corporal (IMC)")
    private Double bodyMassIndex; // IMC (calculado ou persistido)

    @MandatoryField(name = "Relação Cintura/Quadril")
    private Double waistHipRatio; // Relação cintura/quadril (calculado ou persistido)
}