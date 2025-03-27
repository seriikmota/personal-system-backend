package br.ueg.personalsystem.dto.report;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientsWithWithoutAnamneseDTO {
    private Long clientsWithAnamnese;
    private Long clientsWithoutAnamnese;
}