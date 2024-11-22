package br.ueg.personalsystem.service.impl;

import br.ueg.genericarchitecture.exception.Message;
import br.ueg.genericarchitecture.service.impl.AbstractService;
import br.ueg.personalsystem.dto.list.UserListDTO;
import br.ueg.personalsystem.dto.request.UserRequestDTO;
import br.ueg.personalsystem.dto.response.UserResponseDTO;
import br.ueg.personalsystem.entities.User;
import br.ueg.personalsystem.enums.ErrorEnum;
import br.ueg.personalsystem.mapper.UserMapper;
import br.ueg.personalsystem.reflection.ReflectionUtil;
import br.ueg.personalsystem.repository.UserRepository;
import br.ueg.personalsystem.service.IUserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService extends AbstractService<UserRequestDTO, UserResponseDTO, UserListDTO, User, UserRepository, UserMapper, Long>
        implements IUserService {

    @Override
    protected void prepareToCreate(User data) {
        data.setPassword(encryptPassword(data.getPassword()));
    }

    @Override
    protected void prepareToUpdate(User dataDB) {
        dataDB.setPassword(encryptPassword(dataDB.getPassword()));
    }

    @Override
    protected void prepareToDelete(User dataDB) {

    }

    @Override
    protected void validateToMapCreate(UserRequestDTO dto, List<Message> messagesToThrow) {
        this.validateAnnotations(dto, messagesToThrow);
    }

    @Override
    protected void validateToMapUpdate(UserRequestDTO dto, List<Message> messagesToThrow) {
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

    private String encryptPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}
