package br.ueg.personalsystem.dto.report;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientsByGenderDTO {
    private Long maleClients;
    private Long femaleClients;
    private Long otherClients;
}