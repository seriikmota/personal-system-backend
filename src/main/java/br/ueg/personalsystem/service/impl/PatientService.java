package br.ueg.personalsystem.service.impl;

import br.ueg.personalsystem.base.exception.Message;
import br.ueg.personalsystem.base.service.impl.AbstractService;
import br.ueg.personalsystem.dto.list.PatientListDTO;
import br.ueg.personalsystem.dto.request.PatientRequestDTO;
import br.ueg.personalsystem.dto.response.PatientResponseDTO;
import br.ueg.personalsystem.entities.Patient;
import br.ueg.personalsystem.enums.ErrorEnum;
import br.ueg.personalsystem.mapper.PatientMapper;
import br.ueg.personalsystem.reflection.ReflectionUtil;
import br.ueg.personalsystem.repository.PatientRepository;
import br.ueg.personalsystem.service.IPatientService;
import br.ueg.personalsystem.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class PatientService extends AbstractService<PatientRequestDTO, PatientResponseDTO, PatientListDTO, Patient, PatientRepository, PatientMapper, Long>
        implements IPatientService {

    @Autowired
    private PatientRepository repository;

    public Page<Patient> search(String name, String cpf, Pageable pageable) {
        return repository.searchByNameLikeAndCpfLike(Util.toLowerCase(name), Util.removeNonNumericCharacters(cpf), pageable);
    }

    @Override
    protected void prepareToCreate(Patient data) {
        data.setCreatedAt(LocalDateTime.now());
        data.getAddress().setPatient(data);
    }

    @Override
    protected void prepareToUpdate(Patient dataDB) {

    }

    @Override
    protected void prepareToDelete(Patient dataDB) {

    }

    @Override
    protected void prepareToMapCreate(PatientRequestDTO dto) {
        dto.setPhoneNumber(Util.removeNonNumericCharacters(dto.getPhoneNumber()));
        dto.setEmergencyNumber(Util.removeNonNumericCharacters(dto.getEmergencyNumber()));
        dto.setCpf(Util.removeNonNumericCharacters(dto.getCpf()));
        if (dto.getAddress() != null) {
            dto.getAddress().setCep(Util.removeNonNumericCharacters(dto.getAddress().getCep()));
        }
    }

    @Override
    protected void prepareToMapUpdate(PatientRequestDTO dto) {
        dto.setPhoneNumber(Util.removeNonNumericCharacters(dto.getPhoneNumber()));
        dto.setEmergencyNumber(Util.removeNonNumericCharacters(dto.getEmergencyNumber()));
        dto.setCpf(Util.removeNonNumericCharacters(dto.getCpf()));
        if (dto.getAddress() != null) {
            dto.getAddress().setCep(Util.removeNonNumericCharacters(dto.getAddress().getCep()));
        }
    }

    @Override
    protected void validateToMapCreate(PatientRequestDTO dto, List<Message> messagesToThrow) {
        this.validateAnnotations(dto, messagesToThrow);
    }

    @Override
    protected void validateToMapUpdate(PatientRequestDTO dto, List<Message> messagesToThrow) {
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
