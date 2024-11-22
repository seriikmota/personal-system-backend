package br.ueg.personalsystem.mapper;

import br.ueg.genericarchitecture.mapper.GenericMapper;
import br.ueg.personalsystem.dto.list.UserListDTO;
import br.ueg.personalsystem.dto.request.UserRequestDTO;
import br.ueg.personalsystem.dto.response.UserResponseDTO;
import br.ueg.personalsystem.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface UserMapper extends GenericMapper<UserRequestDTO, UserResponseDTO, UserListDTO, User, Long> {
}
