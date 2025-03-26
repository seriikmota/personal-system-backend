package br.ueg.personalsystem.client;

import br.ueg.personalsystem.dto.evolution.ConnectInstanceResponseDTO;
import br.ueg.personalsystem.dto.evolution.ConnectionStatusDTO;
import br.ueg.personalsystem.dto.evolution.CreateInstanceRequestDTO;
import br.ueg.personalsystem.dto.evolution.CreateInstanceResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "evolutionApiClient", url = "${url_evolutionapi}")
public interface EvolutionApiClient {

    @GetMapping("/")
    Object apiInformation();

    @PostMapping("/instance/create")
    CreateInstanceResponseDTO createInstance(@RequestHeader(value = "apiKey") String apiKey, @RequestBody CreateInstanceRequestDTO dto);

    @DeleteMapping("/instance/delete/{instanceName}")
    Object deleteInstance(@RequestHeader(value = "apiKey") String apiKey, @PathVariable(value = "instanceName") String instanceName);

    @GetMapping("/instance/connect/{instanceName}")
    ConnectInstanceResponseDTO connectInstance(@RequestHeader(value = "apiKey") String apiKey, @PathVariable(value = "instanceName") String instanceName);

    @DeleteMapping("/instance/logout/{instanceName}")
    Object logoutInstance(@RequestHeader(value = "apiKey") String apiKey, @PathVariable(value = "instanceName") String instanceName);

    @GetMapping("/instance/connectionState/{instanceName}")
    ConnectionStatusDTO connectionStatus(@RequestHeader(value = "apiKey") String apiKey, @PathVariable(value = "instanceName") String instanceName);

    @PostMapping("/message/sendText/{instance}")
    Object sendMessage(
            @RequestHeader(value = "apiKey") String apiKey,
            @PathVariable(value = "instance") String instance,
            @RequestBody Map<String, Object> messagePayload
    );

    @PostMapping("/chat/checkIsWhatsApp")
    Object checkIsWhatsApp(
            @RequestHeader(value = "apiKey") String apiKey,
            @RequestBody Map<String, Object> payload
    );
}
