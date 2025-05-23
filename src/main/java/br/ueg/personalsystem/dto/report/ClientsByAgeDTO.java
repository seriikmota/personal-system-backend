package br.ueg.personalsystem.dto.report;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientsByAgeDTO {
    private String age;
    private Long clientCount;
}