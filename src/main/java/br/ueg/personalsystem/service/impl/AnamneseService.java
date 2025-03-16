package br.ueg.personalsystem.service.impl;

import br.ueg.genericarchitecture.exception.Message;
import br.ueg.genericarchitecture.service.impl.AbstractService;
import br.ueg.personalsystem.dto.list.AnamneseListDTO;
import br.ueg.personalsystem.dto.request.AnamneseRequestDTO;
import br.ueg.personalsystem.dto.response.AnamneseResponseDTO;
import br.ueg.personalsystem.entities.Anamnese;
import br.ueg.personalsystem.enums.ErrorEnum;
import br.ueg.personalsystem.mapper.AnamneseMapper;
import br.ueg.personalsystem.reflection.ReflectionUtil;
import br.ueg.personalsystem.repository.AnamneseRepository;
import br.ueg.personalsystem.repository.PatientRepository;
import br.ueg.personalsystem.service.IAnamneseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


@Service
public class AnamneseService extends AbstractService<AnamneseRequestDTO, AnamneseResponseDTO, AnamneseListDTO, Anamnese, AnamneseRepository, AnamneseMapper, Long>
        implements IAnamneseService {

    @Autowired
    private PatientRepository repository;


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

    @Override
    protected void validateToMapCreate(AnamneseRequestDTO dto, List<Message> messagesToThrow) {
        this.validateAnnotations(dto, messagesToThrow);
    }

    @Override
    protected void validateToMapUpdate(AnamneseRequestDTO dto, List<Message> messagesToThrow) {
        this.validateAnnotations(dto, messagesToThrow);
    }

    private void validateAnnotations(Object object, List<Message> messagesToThrow) {
        Map<String, List<ErrorEnum>> mapErrors = ReflectionUtil.validateAnnotations(object);
        if (!mapErrors.isEmpty()) {
            for (String fieldKey : mapErrors.keySet()) {
                for (ErrorEnum errorEnum : mapErrors.get(fieldKey)) {
                    messagesToThrow.add(new Message(errorEnum, fieldKey));
                }
            }
        }
    }
}
