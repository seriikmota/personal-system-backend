package br.ueg.personalsystem.controller.impl;

import br.ueg.personalsystem.dto.report.*;
import br.ueg.personalsystem.entities.Anamnese;
import br.ueg.personalsystem.entities.Patient;
import br.ueg.personalsystem.repository.AnamneseRepository;
import br.ueg.personalsystem.repository.PatientRepository;
import br.ueg.personalsystem.service.IReportService;
import br.ueg.personalsystem.service.impl.ExcelExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpHeaders;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("${api.version}/reports")
public class ReportController {

    @Autowired
    private IReportService reportService;

    @Autowired
    private ExcelExportService excelExportService;

    @Autowired
    private AnamneseRepository anamneseRepository;

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/active-inactive-clients")
    public ResponseEntity<ActiveInactiveClientsDTO> getActiveInactiveClients() {
        return ResponseEntity.ok(reportService.getActiveInactiveClients());
    }

    @GetMapping("/clients-by-gender")
    public ResponseEntity<ClientsByGenderDTO> getClientsByGender() {
        return ResponseEntity.ok(reportService.getClientsByGender());
    }

    @GetMapping("/monthly-birthdays")
    public ResponseEntity<List<BirthdayDTO>> getMonthlyBirthdays() {
        return ResponseEntity.ok(reportService.getMonthlyBirthdays());
    }

    @GetMapping("/daily-birthdays")
    public ResponseEntity<List<BirthdayDTO>> getDailyBirthdays() {
        return ResponseEntity.ok(reportService.getDailyBirthdays());
    }

    @GetMapping("/clients-with-without-anamnese")
    public ResponseEntity<ClientsWithWithoutAnamneseDTO> getClientsWithWithoutAnamnese() {
        return ResponseEntity.ok(reportService.getClientsWithWithoutAnamnese());
    }

    @GetMapping("/clients-by-subscription")
    public ResponseEntity<List<ClientsBySubscriptionDTO>> getClientsBySubscription() {
        return ResponseEntity.ok(reportService.getClientsBySubscription());
    }

    @GetMapping("/clients-by-age")
    public ResponseEntity<List<ClientsByAgeDTO>> getClientsByAge() {
        return ResponseEntity.ok(reportService.getClientsByAge());
    }

    @GetMapping("/clients-by-city")
    public ResponseEntity<List<ClientsByCityDTO>> getClientsByCity() {
        return ResponseEntity.ok(reportService.getClientsByCity());
    }

    @GetMapping("/client-growth-with-anamnese")
    public ResponseEntity<List<AnamneseCountByDayDTO>> getClientGrowthWithAnamnese() {
        return ResponseEntity.ok(reportService.getClientGrowthWithAnamnese());
    }

    @GetMapping("/monthly-profit-estimate")
    public ResponseEntity<ProfitEstimateDTO> getMonthlyProfitEstimate() {
        return ResponseEntity.ok(reportService.getMonthlyProfitEstimate());
    }

    @GetMapping("/exportar/excel")
    public ResponseEntity<byte[]> exportarExcel() {
        try {
            List<Anamnese> anamneses = anamneseRepository.findAll();
            List<Patient> pacientes = patientRepository.findAll();

            byte[] xlsxContent = excelExportService.criarPlanilhaXLSX(anamneses, pacientes);

            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String fileName = "Relatorio_Anamnese_Clientes_" + timestamp + ".xlsx";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
            headers.setContentDispositionFormData("attachment", fileName);

            return ResponseEntity.ok().headers(headers).body(xlsxContent);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(null);
        }
    }
}