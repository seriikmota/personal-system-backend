package br.ueg.personalsystem.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "documents")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String hash;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String signature;

    @Column(nullable = false)
    private LocalDate signedDate;

    @ManyToOne(optional = false)
    @JoinColumn()
    private User signedUser;
}
