package br.ueg.personalsystem.service.validations.anamnese;


import br.ueg.personalsystem.base.enums.ValidationActionsEnum;
import br.ueg.personalsystem.base.exception.Message;
import br.ueg.personalsystem.base.validation.IValidations;
import br.ueg.personalsystem.entities.Anamnese;
import br.ueg.personalsystem.enums.ErrorEnum;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AnamneseMeasurementsValidation implements IValidations<Anamnese> {

    @Override
    public void validate(Anamnese data, ValidationActionsEnum action, List<Message> messagesToThrow) {
        validateWeight(data.getWeight(), messagesToThrow);
        validateHeight(data.getHeight(), messagesToThrow);
        validateWaistCircumference(data.getWaistCircumference(), messagesToThrow);
        validateHipCircumference(data.getHipCircumference(), messagesToThrow);
        validateBodyFatPercentage(data.getBodyFatPercentage(), messagesToThrow);
        validateMuscleMass(data.getMuscleMass(), messagesToThrow);
        validateBodyMassIndex(data.getBodyMassIndex(), messagesToThrow);
        validateWaistHipRatio(data.getWaistHipRatio(), messagesToThrow);
    }

    private void validateWeight(Double weight, List<Message> messagesToThrow) {
        if (weight != null && weight <= 0) {
            messagesToThrow.add(new Message(ErrorEnum.INVALID_WEIGHT));
        }
    }

    private void validateHeight(Double height, List<Message> messagesToThrow) {
        if (height != null && height <= 0) {
            messagesToThrow.add(new Message(ErrorEnum.INVALID_HEIGHT));
        }
    }

    private void validateWaistCircumference(Double waistCircumference, List<Message> messagesToThrow) {
        if (waistCircumference != null && waistCircumference <= 0) {
            messagesToThrow.add(new Message(ErrorEnum.INVALID_WAIST_CIRCUMFERENCE));
        }
    }

    private void validateHipCircumference(Double hipCircumference, List<Message> messagesToThrow) {
        if (hipCircumference != null && hipCircumference <= 0) {
            messagesToThrow.add(new Message(ErrorEnum.INVALID_HIP_CIRCUMFERENCE));
        }
    }

    private void validateBodyFatPercentage(Double bodyFatPercentage, List<Message> messagesToThrow) {
        if (bodyFatPercentage != null && (bodyFatPercentage < 0 || bodyFatPercentage > 100)) {
            messagesToThrow.add(new Message(ErrorEnum.INVALID_BODY_FAT_PERCENTAGE));
        }
    }

    private void validateMuscleMass(Double muscleMass, List<Message> messagesToThrow) {
        if (muscleMass != null && muscleMass <= 0) {
            messagesToThrow.add(new Message(ErrorEnum.INVALID_MUSCLE_MASS));
        }
    }

    private void validateBodyMassIndex(Double bodyMassIndex, List<Message> messagesToThrow) {
        if (bodyMassIndex != null && bodyMassIndex <= 0) {
            messagesToThrow.add(new Message(ErrorEnum.INVALID_BODY_MASS_INDEX));
        }
    }

    private void validateWaistHipRatio(Double waistHipRatio, List<Message> messagesToThrow) {
        if (waistHipRatio != null && waistHipRatio <= 0) {
            messagesToThrow.add(new Message(ErrorEnum.INVALID_WAIST_HIP_RATIO));
        }
    }
}
