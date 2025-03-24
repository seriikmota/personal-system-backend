package br.ueg.personalsystem.dto.evolution;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Map;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ConnectionStatusDTO {
    private String instanceName;
    private String status;

    @JsonProperty("instance")
    private void unpackInstance(Map<String, Object> instance) {
        this.instanceName = (String) instance.get("instanceName");
        this.status = (String) instance.get("state");
    }
}
