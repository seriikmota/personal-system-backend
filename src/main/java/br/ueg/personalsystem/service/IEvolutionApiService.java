package br.ueg.personalsystem.service;

import br.ueg.personalsystem.dto.evolution.ConnectInstanceResponseDTO;
import br.ueg.personalsystem.dto.evolution.ConnectionStatusDTO;

public interface IEvolutionApiService {
    Object apiInformation();
    void createInstance(Long userId, String instanceName);
    void deleteInstance(Long userId);
    ConnectInstanceResponseDTO connectInstance();
    void logoutInstance();
    ConnectionStatusDTO connectionStatus();
    void sendMessage(String instanceName, String number, String text);
    Boolean checkIsWhatsApp(String number);
}
