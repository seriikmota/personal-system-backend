package br.ueg.personalsystem.service.impl;

import br.ueg.personalsystem.base.service.impl.AbstractService;
import br.ueg.personalsystem.dto.list.AnamneseListDTO;
import br.ueg.personalsystem.dto.request.AnamneseRequestDTO;
import br.ueg.personalsystem.dto.response.AnamneseResponseDTO;
import br.ueg.personalsystem.entities.Anamnese;
import br.ueg.personalsystem.mapper.AnamneseMapper;
import br.ueg.personalsystem.repository.AnamneseRepository;
import br.ueg.personalsystem.repository.PatientRepository;
import br.ueg.personalsystem.service.IAnamneseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Service
public class AnamneseService extends AbstractService<AnamneseRequestDTO, AnamneseResponseDTO, AnamneseListDTO, Anamnese, AnamneseRepository, AnamneseMapper, Long>
        implements IAnamneseService {

    @Autowired
    private AnamneseRepository repository;

    public Page<Anamnese> search(Long patientId, LocalDate startDate, LocalDate endDate, Pageable pageable) {
        return repository.searchAnamnese(patientId, startDate, endDate, pageable);
    }

    @Override
    protected void prepareToMapCreate(AnamneseRequestDTO dto) {
       dto.setBodyMassIndex(dto.getWeight() / (dto.getHeight() * dto.getHeight()));
       dto.setWaistHipRatio(dto.getWaistCircumference()/ dto.getWaistCircumference());
       dto.setAnamnesisDate(LocalDate.from(LocalDateTime.now()));
    }

    @Override
    protected void prepareToMapUpdate(AnamneseRequestDTO dto) {
        dto.setBodyMassIndex(dto.getWeight() / (dto.getHeight() * dto.getHeight()));
        dto.setWaistHipRatio(dto.getWaistCircumference()/ dto.getWaistCircumference());
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


}
