package br.ueg.personalsystem.mapper;

import br.ueg.genericarchitecture.mapper.GenericMapper;
import br.ueg.personalsystem.dto.list.PatientListDTO;
import br.ueg.personalsystem.dto.request.PatientRequestDTO;
import br.ueg.personalsystem.dto.response.PatientResponseDTO;
import br.ueg.personalsystem.entities.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface PatientMapper extends GenericMapper<PatientRequestDTO, PatientResponseDTO, PatientListDTO, Patient, Long> {
}
