package br.ueg.personalsystem.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "evolutionApiClient", url = "${url_evolutionapi}")
public interface EvolutionApiClient {

    @GetMapping("/")
    Object apiInformation();

    @GetMapping("/instance/connect/{instanceName}")
    Object connectInstance(@RequestHeader(value = "apiKey") String apiKey, @PathVariable(value = "instanceName") String instanceName);

    @DeleteMapping("/instance/logout/{instanceName}")
    Object logoutInstance(@RequestHeader(value = "apiKey") String apiKey, @PathVariable(value = "instanceName") String instanceName);

}
