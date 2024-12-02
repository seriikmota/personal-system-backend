package br.ueg.personalsystem.mapper;

import br.ueg.genericarchitecture.mapper.GenericMapper;
import br.ueg.personalsystem.dto.list.AddressListDTO;
import br.ueg.personalsystem.dto.request.AddressRequestDTO;
import br.ueg.personalsystem.dto.response.AddressResponseDTO;
import br.ueg.personalsystem.entities.Address;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface AddressMapper extends GenericMapper<AddressRequestDTO, AddressResponseDTO, AddressListDTO, Address, Long> {
}
