package br.ueg.personalsystem.controller.impl;

import br.ueg.personalsystem.base.controller.impl.AbstractCrudController;
import br.ueg.personalsystem.controller.IUserController;
import br.ueg.personalsystem.dto.list.UserListDTO;
import br.ueg.personalsystem.dto.request.UserRequestDTO;
import br.ueg.personalsystem.dto.response.UserResponseDTO;
import br.ueg.personalsystem.entities.User;
import br.ueg.personalsystem.mapper.UserMapper;
import br.ueg.personalsystem.service.impl.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "${api.version}/user")
public class UserController extends AbstractCrudController<UserRequestDTO, UserResponseDTO, UserListDTO, User, UserService, UserMapper, Long>
        implements IUserController {
}
