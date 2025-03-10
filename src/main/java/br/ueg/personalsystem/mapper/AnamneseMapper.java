package br.ueg.personalsystem.mapper;

import br.ueg.genericarchitecture.mapper.GenericMapper;
import br.ueg.personalsystem.dto.list.AnamneseListDTO;
import br.ueg.personalsystem.dto.request.AnamneseRequestDTO;
import br.ueg.personalsystem.dto.response.AnamneseResponseDTO;
import br.ueg.personalsystem.entities.Anamnese;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = PatientMapper.class
)
public interface AnamneseMapper  extends GenericMapper<AnamneseRequestDTO, AnamneseResponseDTO, AnamneseListDTO, Anamnese, Long> {
}
