package br.ueg.personalsystem.dto.evolution;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class EvolutionSendMessageDTO {
    private String number;
    private String text;
}
