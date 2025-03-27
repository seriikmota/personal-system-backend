package br.ueg.personalsystem.service.impl;

import br.ueg.personalsystem.dto.report.*;
import br.ueg.personalsystem.repository.PatientRepository;
import br.ueg.personalsystem.service.IReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReportService implements IReportService {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public ActiveInactiveClientsDTO getActiveInactiveClients() {
        Long activeClients = patientRepository.countByEnabled(true);
        Long inactiveClients = patientRepository.countByEnabled(false);
        return new ActiveInactiveClientsDTO(activeClients, inactiveClients);
    }

    @Override
    public ClientsByGenderDTO getClientsByGender() {
        Long maleClients = patientRepository.countByGender("Male");
        Long femaleClients = patientRepository.countByGender("Female");
        return new ClientsByGenderDTO(maleClients, femaleClients);
    }

    @Override
    public List<BirthdayDTO> getMonthlyBirthdays() {
        return patientRepository.findMonthlyBirthdays();
    }

    @Override
    public List<BirthdayDTO> getDailyBirthdays() {
        return patientRepository.findDailyBirthdays();
    }

    @Override
    public ClientsWithWithoutAnamneseDTO getClientsWithWithoutAnamnese() {
        Long clientsWithAnamnese = patientRepository.countClientsWithAnamnese();
        Long clientsWithoutAnamnese = patientRepository.countClientsWithoutAnamnese();
        return new ClientsWithWithoutAnamneseDTO(clientsWithAnamnese, clientsWithoutAnamnese);
    }

    @Override
    public List<ClientsBySubscriptionDTO> getClientsBySubscription() {
        return patientRepository.findClientsBySubscription();
    }

    @Override
    public List<ClientsByAgeDTO> getClientsByAge() {
        return patientRepository.findClientsByAge();
    }

    @Override
    public List<ClientsByCityDTO> getClientsByCity() {
        return patientRepository.findClientsByCity();
    }

    @Override
    public List<ClientGrowthDTO> getClientGrowthWithAnamnese() {
        return patientRepository.findClientGrowthWithAnamnese();
    }

    @Override
    public List<ClientGrowthDTO> getActiveClientGrowth() {
        return patientRepository.findActiveClientGrowth();
    }
}