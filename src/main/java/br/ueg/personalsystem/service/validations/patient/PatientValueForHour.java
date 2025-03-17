package br.ueg.personalsystem.service.validations.patient;

import br.ueg.personalsystem.base.enums.ValidationActionsEnum;
import br.ueg.personalsystem.base.exception.Message;
import br.ueg.personalsystem.base.validation.IValidations;
import br.ueg.personalsystem.entities.Patient;
import br.ueg.personalsystem.enums.ErrorEnum;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PatientValueForHour implements IValidations<Patient> {
    @Override
    public void validate(Patient data, ValidationActionsEnum action, List<Message> messagesToThrow) {
        if (data.getValueForHour() != null) {
            if (data.getValueForHour() <= 0) {
                messagesToThrow.add(new Message(ErrorEnum.PATIENT_VALUE_HOUR_INVALID));
            }
        }
    }
}
