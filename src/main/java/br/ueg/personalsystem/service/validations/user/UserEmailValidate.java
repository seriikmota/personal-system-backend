package br.ueg.personalsystem.service.validations.user;

import br.ueg.genericarchitecture.enums.ValidationActionsEnum;
import br.ueg.genericarchitecture.exception.Message;
import br.ueg.genericarchitecture.validation.IValidations;
import br.ueg.personalsystem.entities.User;
import br.ueg.personalsystem.enums.ErrorEnum;
import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserEmailValidate implements IValidations<User> {
    @Override
    public void validate(User data, ValidationActionsEnum action, List<Message> messagesToThrow) {
        if (action.equals(ValidationActionsEnum.CREATE) || action.equals(ValidationActionsEnum.UPDATE)) {
            if (data.getEmail() != null) {
                EmailValidator emailValidator = new EmailValidator();
                emailValidator.isValid(data.getEmail(), null);
                messagesToThrow.add(new Message(ErrorEnum.EMAIL_EXISTS));
            }
        }
    }
}
