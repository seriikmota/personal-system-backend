package br.ueg.personalsystem.service;

import br.ueg.personalsystem.dto.report.*;

import java.util.List;

public interface IReportService {
    ActiveInactiveClientsDTO getActiveInactiveClients();
    ClientsByGenderDTO getClientsByGender();
    List<BirthdayDTO> getMonthlyBirthdays();
    List<BirthdayDTO> getDailyBirthdays();
    ClientsWithWithoutAnamneseDTO getClientsWithWithoutAnamnese();
    List<ClientsBySubscriptionDTO> getClientsBySubscription();
    List<ClientsByAgeDTO> getClientsByAge();
    List<ClientsByCityDTO> getClientsByCity();
    List<AnamneseCountByDayDTO> getClientGrowthWithAnamnese();
//    List<AnamneseCountByDayDTO> getActiveClientGrowth();
    ProfitEstimateDTO getMonthlyProfitEstimate();
}