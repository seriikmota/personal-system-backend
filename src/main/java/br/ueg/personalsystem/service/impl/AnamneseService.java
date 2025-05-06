package br.ueg.personalsystem.service.impl;

import br.ueg.personalsystem.base.service.impl.AbstractService;
import br.ueg.personalsystem.dto.list.AnamneseListDTO;
import br.ueg.personalsystem.dto.list.DocumentDTO;
import br.ueg.personalsystem.dto.request.AnamneseRequestDTO;
import br.ueg.personalsystem.dto.response.AnamneseResponseDTO;
import br.ueg.personalsystem.entities.Anamnese;
import br.ueg.personalsystem.mapper.AnamneseMapper;
import br.ueg.personalsystem.repository.AnamneseRepository;
import br.ueg.personalsystem.service.IAnamneseService;
import br.ueg.personalsystem.service.ISignatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Service
public class AnamneseService extends AbstractService<AnamneseRequestDTO, AnamneseResponseDTO, AnamneseListDTO, Anamnese, AnamneseRepository, AnamneseMapper, Long>
        implements IAnamneseService {

    @Autowired
    private AnamneseRepository repository;

    @Autowired
    private ISignatureService signatureService;

    public Page<Anamnese> search(String patientName, LocalDate startDate, LocalDate endDate, Pageable pageable) {
        return repository.searchAnamnese(patientName, startDate, endDate, pageable);
    }

    @Override
    protected void prepareToMapCreate(AnamneseRequestDTO dto) {
       dto.setBodyMassIndex(dto.getWeight() / (dto.getHeight() * dto.getHeight()));
       dto.setWaistHipRatio(dto.getWaistCircumference()/ dto.getHipCircumference());
       dto.setAnamnesisDate(LocalDate.from(LocalDateTime.now()));
    }

    @Override
    protected void prepareToMapUpdate(AnamneseRequestDTO dto) {
        dto.setBodyMassIndex(dto.getWeight() / (dto.getHeight() * dto.getHeight()));
        dto.setWaistHipRatio(dto.getWaistCircumference()/ dto.getHipCircumference());
        dto.setAnamnesisDate(LocalDate.from(LocalDateTime.now()));
    }

    @Override
    protected void prepareToCreate(Anamnese data) {

    }

    @Override
    protected void prepareToUpdate(Anamnese dataDB) {

    }

    @Override
    protected void prepareToDelete(Anamnese dataDB) {

    }

    @Override
    public DocumentDTO export(Long id) {
        Anamnese anamnese = this.validateIdModelExistsAndGet(id);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8))) {
            writer.println("Dados da Anamnese - " + anamnese.getId());
            writer.println("");
            writer.println("Paciente: " + anamnese.getPatient().getName());
            writer.println("Data: " + anamnese.getAnamnesisDate().format(formatter));
            writer.println("Principais Reclamações: " + anamnese.getMainComplaints());
            writer.println("Histórico Médico: " + anamnese.getMedicalHistory());
            writer.println("Observações: " + (anamnese.getObservations() != null ? anamnese.getObservations() : "None"));
            writer.println("Peso: " + anamnese.getWeight() + " kg");
            writer.println("Altura: " + anamnese.getHeight() + " m");
            writer.println("Circunferência da cintura: " + anamnese.getWaistCircumference() + " cm");
            writer.println("Circunferência do quadril: " + anamnese.getHipCircumference() + " cm");
            writer.println("Porcentagem de gordura corporal: " + anamnese.getBodyFatPercentage() + " %");
            writer.println("Massa muscular: " + anamnese.getMuscleMass() + " kg");
            writer.println("IMC: " + anamnese.getBodyMassIndex());
            writer.println("Relação Cintura-Quadril: " + anamnese.getWaistHipRatio());
        }

        DateTimeFormatter formatterHour = DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm-ss");
        String filename = "Anamnese de " + anamnese.getPatient().getName() + " - " + LocalDateTime.now().format(formatterHour) + ".txt";
        byte[] documentSigned = signatureService.sign(filename, outputStream.toByteArray());
        return DocumentDTO.builder().name(filename).bytes(documentSigned).build();
    }

    @Override
    public String validate(String filename, byte[] documentBytes) {
        return signatureService.validateSignature(filename, documentBytes);
    }
}
