package br.ueg.personalsystem.service.impl;

import br.ueg.personalsystem.base.exception.BusinessException;
import br.ueg.personalsystem.client.EvolutionApiClient;
import br.ueg.personalsystem.dto.evolution.*;
import br.ueg.personalsystem.entities.EvolutionInstance;
import br.ueg.personalsystem.entities.Patient;
import br.ueg.personalsystem.entities.User;
import br.ueg.personalsystem.enums.ErrorEnum;
import br.ueg.personalsystem.service.IEvolutionApiService;
import br.ueg.personalsystem.service.IUserService;
import br.ueg.personalsystem.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EvolutionApiService implements IEvolutionApiService {

    @Autowired
    private EvolutionApiClient client;

    @Autowired
    private IUserService userService;

    @Value("${globalApikey_evolutionapi}")
    private String globalApiKey;

    @Value("${integration_evolutionapi}")
    private String integration;

    @Override
    public Object apiInformation() {
        return client.apiInformation();
    }

    @Override
    public void createInstance(Long userId, String instanceName) {
        if (!this.instanceIsValid(userService.getEvolutionInstanceByUserId(userId))) {
            CreateInstanceRequestDTO requestDTO = new CreateInstanceRequestDTO();
            requestDTO.setInstanceName(instanceName);
            requestDTO.setQrcode(false);
            requestDTO.setIntegration(integration);
            CreateInstanceResponseDTO response = client.createInstance(globalApiKey, requestDTO);

            User user = userService.getById(userId);
            user.setApiKeyEvolution(response.getInstance_hash());
            user.setInstanceNameEvolution(response.getInstance_name());
        } else {
            throw new BusinessException(ErrorEnum.USER_HASH_INSTANCE);
        }
    }

    @Override
    public void deleteInstance(Long userId) {
        EvolutionInstance instance = userService.getEvolutionInstanceByUserId(userId);
        if (instance == null || (Util.isNullOrEmpty(instance.getInstanceName()) && Util.isNullOrEmpty(instance.getInstanceApiKey()))) {
            throw new BusinessException(ErrorEnum.USER_NOT_HAVE_INSTANCE);
        }
        client.deleteInstance(globalApiKey, instance.getInstanceName());

        User user = userService.getById(Util.getIdUserLogged());
        user.setApiKeyEvolution(null);
        user.setInstanceNameEvolution(null);
    }

    @Override
    public ConnectInstanceResponseDTO connectInstance() {
        EvolutionInstance instance = userService.getEvolutionInstanceByUserId(Util.getIdUserLogged());
        if (instance == null || (Util.isNullOrEmpty(instance.getInstanceName()) && Util.isNullOrEmpty(instance.getInstanceApiKey()))) {
            throw new BusinessException(ErrorEnum.YOU_NOT_HAVE_INSTANCE);
        }
        return client.connectInstance(instance.getInstanceApiKey(), instance.getInstanceName());
    }

    @Override
    public void logoutInstance() {
        EvolutionInstance instance = userService.getEvolutionInstanceByUserId(Util.getIdUserLogged());
        if (instance == null || (Util.isNullOrEmpty(instance.getInstanceName()) && Util.isNullOrEmpty(instance.getInstanceApiKey()))) {
            throw new BusinessException(ErrorEnum.YOU_NOT_HAVE_INSTANCE);
        }
        client.logoutInstance(instance.getInstanceApiKey(), instance.getInstanceName());
    }

    @Override
    public ConnectionStatusDTO connectionStatus() {
        EvolutionInstance instance = userService.getEvolutionInstanceByUserId(Util.getIdUserLogged());
        if (instance == null || (Util.isNullOrEmpty(instance.getInstanceName()) && Util.isNullOrEmpty(instance.getInstanceApiKey()))) {
            throw new BusinessException(ErrorEnum.YOU_NOT_HAVE_INSTANCE);
        }
        return client.connectionStatus(instance.getInstanceApiKey(), instance.getInstanceName());
    }

    public Boolean instanceIsValid(EvolutionInstance instance) {
        return instance != null && !Util.isNullOrEmpty(instance.getInstanceName()) && !Util.isNullOrEmpty(instance.getInstanceApiKey());
    }

    public void sendMessages(PatientSendMessageDTO dto) {
        EvolutionInstance instance = userService.getEvolutionInstanceByUserId(Util.getIdUserLogged());

        if (instance == null || Util.isNullOrEmpty(instance.getInstanceName()) || Util.isNullOrEmpty(instance.getInstanceApiKey())) {
            throw new BusinessException(ErrorEnum.YOU_NOT_HAVE_INSTANCE);
        }

        for (Patient patient : dto.getPatients()) {
            client.sendMessage(instance.getInstanceApiKey(), instance.getInstanceName(), new EvolutionSendMessageDTO("55"+patient.getPhoneNumber(), dto.getMessage()));
        }
    }

    public Boolean checkIsWhatsApp(String number) {
        EvolutionInstance instance = userService.getEvolutionInstanceByUserId(Util.getIdUserLogged());

        if (instance == null || Util.isNullOrEmpty(instance.getInstanceName()) || Util.isNullOrEmpty(instance.getInstanceApiKey())) {
            throw new BusinessException(ErrorEnum.YOU_NOT_HAVE_INSTANCE);
        }

        Map<String, Object> payload = new HashMap<>();
        payload.put("number", number);

        Object response = client.checkIsWhatsApp(instance.getInstanceApiKey(), payload);

        return response instanceof Boolean && (Boolean) response; // para evitar nulos
    }
}
