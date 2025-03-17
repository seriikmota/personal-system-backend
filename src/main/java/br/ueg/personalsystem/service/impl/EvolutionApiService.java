package br.ueg.personalsystem.service.impl;

import br.ueg.personalsystem.clients.EvolutionApiClient;
import br.ueg.personalsystem.service.IEvolutionApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvolutionApiService implements IEvolutionApiService {

    @Autowired
    private EvolutionApiClient client;

    @Override
    public Object apiInformation() {
        return client.apiInformation();
    }
}
