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
public class CreateInstanceResponseDTO {

    @JsonProperty(value = "hash")
    private String instance_hash;
    private String instance_name;
    private String qrcode_code;
    private String qrcode_base64;

    @JsonProperty("instance")
    private void unpackInstance(Map<String, Object> instance) {
        this.instance_name = (String) instance.get("instanceName");
    }

    @JsonProperty("qrcode")
    private void unpackQrcode(Map<String, Object> qrcode) {
        this.qrcode_code = (String) qrcode.get("code");
        this.qrcode_base64 = (String) qrcode.get("base64");
    }

}
