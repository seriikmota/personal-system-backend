package br.ueg.personalsystem.client;

import br.ueg.personalsystem.dto.evolution.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/message/sendText/{instanceName}")
    Object sendMessage(@RequestHeader(value = "apiKey") String apiKey, @PathVariable(value = "instanceName") String instanceName, @RequestBody EvolutionSendMessageDTO evolutionSendMessageDTO);
}
