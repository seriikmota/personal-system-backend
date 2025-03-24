package br.ueg.personalsystem.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class EvolutionInstance {
    private String instanceName;
    private String instanceApiKey;

    public EvolutionInstance(String instanceName, String instanceApiKey) {
        this.instanceName = instanceName;
        this.instanceApiKey = instanceApiKey;
    }
}
