package br.ueg.personalsystem.service.validations.patient;

import br.ueg.genericarchitecture.enums.ValidationActionsEnum;
import br.ueg.genericarchitecture.exception.Message;
import br.ueg.genericarchitecture.validation.IValidations;
import br.ueg.personalsystem.entities.Patient;
import br.ueg.personalsystem.enums.ErrorEnum;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class PatientBirthDate implements IValidations<Patient> {

    @Override
    public void validate(Patient data, ValidationActionsEnum action, List<Message> messagesToThrow) {
        if (data.getBirthDate() != null) {
            if (data.getBirthDate().isAfter(LocalDate.now())) {
                messagesToThrow.add(new Message(ErrorEnum.BIRTH_DAY_IS_AFTER));
            }
        }
    }
}
