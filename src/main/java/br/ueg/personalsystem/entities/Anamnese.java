package br.ueg.personalsystem.entities;

import br.ueg.personalsystem.base.domain.GenericModel;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "anamnese")
public class Anamnese implements GenericModel<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "fk_patient_id"))
    private Patient patient;

    @Column(nullable = false)
    private LocalDate anamnesisDate;

    @Column(nullable = false)
    private String mainComplaints;

    @Column(nullable = false)
    private String medicalHistory;

    @Column
    private String observations;

    @Column(nullable = false)
    private Double weight;

    @Column(nullable = false)
    private Double height;

    @Column(nullable = false)
    private Double waistCircumference;

    @Column(nullable = false)
    private Double hipCircumference;

    @Column(nullable = false)
    private Double bodyFatPercentage;

    @Column(nullable = false)
    private Double muscleMass;

    @Column(nullable = false)
    private Double bodyMassIndex;

    @Column(nullable = false)
    private Double waistHipRatio;
}

