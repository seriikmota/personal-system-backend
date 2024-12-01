package br.ueg.personalsystem.entities;

import br.ueg.genericarchitecture.domain.GenericModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "patient")
public class Patient implements GenericModel<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String maritalStatus;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String emergencyNumber;

    @Column(nullable = false)
    private String profession;

    @Column(nullable = false)
    private Double valueForHour;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}
