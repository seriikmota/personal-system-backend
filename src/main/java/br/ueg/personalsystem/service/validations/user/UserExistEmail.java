package br.ueg.personalsystem.service.validations.user;

import br.ueg.personalsystem.base.enums.ValidationActionsEnum;
import br.ueg.personalsystem.base.exception.Message;
import br.ueg.personalsystem.base.validation.IValidations;
import br.ueg.personalsystem.entities.User;
import br.ueg.personalsystem.enums.ErrorEnum;
import br.ueg.personalsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserExistEmail implements IValidations<User> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void validate(User data, ValidationActionsEnum action, List<Message> messagesToThrow) {
        if (action.equals(ValidationActionsEnum.CREATE) || action.equals(ValidationActionsEnum.UPDATE)) {
            if (data.getEmail() != null && userRepository.existsByEmail(data.getEmail())) {
                messagesToThrow.add(new Message(ErrorEnum.EMAIL_EXISTS));
            }
        }
    }
}
