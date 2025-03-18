package br.ueg.personalsystem.service.impl;

import br.ueg.personalsystem.clients.EvolutionApiClient;
import br.ueg.personalsystem.entities.User;
import br.ueg.personalsystem.service.IEvolutionApiService;
import br.ueg.personalsystem.service.IUserService;
import br.ueg.personalsystem.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvolutionApiService implements IEvolutionApiService {

    @Autowired
    private EvolutionApiClient client;

    @Autowired
    private IUserService userService;

    @Override
    public Object apiInformation() {
        return client.apiInformation();
    }

    @Override
    public Object connectInstance() {
        User user = userService.getById(Util.getIdUserLogged());
        return client.connectInstance(user.getApiKeyEvolution(), user.getInstanceNameEvolution());
    }

    @Override
    public Object logoutInstance() {
        User user = userService.getById(Util.getIdUserLogged());
        return client.logoutInstance(user.getApiKeyEvolution(), user.getInstanceNameEvolution());
    }
}
