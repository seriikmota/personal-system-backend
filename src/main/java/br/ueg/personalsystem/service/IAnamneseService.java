package br.ueg.personalsystem.service;

import br.ueg.personalsystem.base.service.IAbstractService;
import br.ueg.personalsystem.dto.request.AnamneseRequestDTO;
import br.ueg.personalsystem.entities.Anamnese;

public interface IAnamneseService extends IAbstractService<AnamneseRequestDTO, Anamnese, Long> {
}
