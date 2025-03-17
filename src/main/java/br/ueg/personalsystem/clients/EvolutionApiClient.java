package br.ueg.personalsystem.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "evolutionApiClient", url = "${url_evolutionapi}")
public interface EvolutionApiClient {

    @GetMapping("/")
    Object apiInformation();

}
