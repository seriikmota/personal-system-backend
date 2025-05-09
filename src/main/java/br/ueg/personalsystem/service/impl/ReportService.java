package br.ueg.personalsystem.service.impl;

import br.ueg.personalsystem.dto.report.*;
import br.ueg.personalsystem.entities.Patient;
import br.ueg.personalsystem.repository.AnamneseRepository;
import br.ueg.personalsystem.repository.PatientRepository;
import br.ueg.personalsystem.service.IReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportService implements IReportService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AnamneseRepository anamneseRepository;

    @Override
    public ActiveInactiveClientsDTO getActiveInactiveClients() {
        Long activeClients = patientRepository.countByEnabled(true);
        Long inactiveClients = patientRepository.countByEnabled(false);
        return new ActiveInactiveClientsDTO(activeClients, inactiveClients);
    }

    @Override
    public ClientsByGenderDTO getClientsByGender() {
        Long maleClients = patientRepository.countByGender("Masculino");
        Long femaleClients = patientRepository.countByGender("Feminino");
        Long otherClients = patientRepository.countByGender("Outros");
        return new ClientsByGenderDTO(maleClients, femaleClients, otherClients);
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
        List<Patient> patients = patientRepository.findAll();
        Map<Integer, Long> ageCountMap = patients.stream()
                .map(patient -> calculateAge(patient.getBirthDate()))
                .collect(Collectors.groupingBy(age -> age, Collectors.counting()));

        return ageCountMap.entrySet().stream()
                .map(entry -> new ClientsByAgeDTO(entry.getKey().toString(), entry.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public List<AnamneseCountByDayDTO> getClientGrowthWithAnamnese() {
        return anamneseRepository.findAnamneseCountByDay();
    }

    @Override
    public List<ClientsByCityDTO> getClientsByCity() {
        return patientRepository.findClientsByCity();
    }

//    @Override
//    public List<AnamneseCountByDayDTO> getActiveClientGrowth() {
//        return patientRepository.findActiveClientGrowth();
//    }

    @Override
    public ProfitEstimateDTO getMonthlyProfitEstimate() {
        List<Patient> activePatients = patientRepository.findAll().stream()
                .filter(patient -> patient.getEnabled() && patient.getClassesPerMonth() != null && patient.getValueForHour() != null)
                .collect(Collectors.toList());

        List<PatientProfitEstimateDTO> patientProfits = activePatients.stream()
                .map(patient -> new PatientProfitEstimateDTO(
                        patient.getId(),
                        patient.getName(),
                        patient.getClassesPerMonth() * patient.getValueForHour(),
                        patient.getClassesPerMonth()
                ))
                .collect(Collectors.toList());

        double totalProfit = patientProfits.stream()
                .mapToDouble(PatientProfitEstimateDTO::getProfitEstimate)
                .sum();

        long totalClasses = activePatients.stream()
                .mapToLong(Patient::getClassesPerMonth)
                .sum();

        return new ProfitEstimateDTO(totalProfit, totalClasses, patientProfits);
    }


    private int calculateAge(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }
}