package br.ueg.personalsystem.service;

import br.ueg.personalsystem.base.service.IAbstractService;
import br.ueg.personalsystem.dto.request.UserRequestDTO;
import br.ueg.personalsystem.entities.User;

public interface IUserService extends IAbstractService<UserRequestDTO, User, Long> {
}
