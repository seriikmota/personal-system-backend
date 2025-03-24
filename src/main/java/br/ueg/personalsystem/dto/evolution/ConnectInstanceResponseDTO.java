package br.ueg.personalsystem.dto.evolution;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ConnectInstanceResponseDTO {

    @JsonProperty(value = "code")
    private String qrcode_code;

    @JsonProperty(value = "base64")
    private String qrcode_base64;

}
