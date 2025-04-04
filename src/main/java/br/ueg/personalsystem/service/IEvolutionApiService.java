package br.ueg.personalsystem.service;

import br.ueg.personalsystem.dto.evolution.ConnectInstanceResponseDTO;
import br.ueg.personalsystem.dto.evolution.ConnectionStatusDTO;
import br.ueg.personalsystem.dto.evolution.PatientSendMessageDTO;

public interface IEvolutionApiService {
    Object apiInformation();
    void createInstance(Long userId, String instanceName);
    void deleteInstance(Long userId);
    ConnectInstanceResponseDTO connectInstance();
    void logoutInstance();
    ConnectionStatusDTO connectionStatus();
    void sendMessages(PatientSendMessageDTO dto);
    Boolean checkIsWhatsApp(String number);
}
