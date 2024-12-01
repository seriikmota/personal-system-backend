package br.ueg.personalsystem.service.validations.patient;

import br.ueg.genericarchitecture.enums.ValidationActionsEnum;
import br.ueg.genericarchitecture.exception.Message;
import br.ueg.genericarchitecture.validation.IValidations;
import br.ueg.personalsystem.entities.Patient;
import br.ueg.personalsystem.enums.ErrorEnum;
import br.ueg.personalsystem.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PatientCpfExist implements IValidations<Patient> {

    @Autowired
    private PatientRepository repository;

    @Override
    public void validate(Patient data, ValidationActionsEnum action, List<Message> messagesToThrow) {
        if (data.getCpf() != null && action.equals(ValidationActionsEnum.CREATE)) {
            if (repository.existsPatientByCpf(data.getCpf())) {
                messagesToThrow.add(new Message(ErrorEnum.CPF_EXIST));
            }
        }
    }
}
