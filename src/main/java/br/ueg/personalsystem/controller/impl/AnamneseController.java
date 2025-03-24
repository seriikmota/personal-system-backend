package br.ueg.personalsystem.controller.impl;

import br.ueg.personalsystem.base.controller.impl.AbstractCrudController;
import br.ueg.personalsystem.controller.IAnamneseController;
import br.ueg.personalsystem.dto.list.AnamneseListDTO;
import br.ueg.personalsystem.dto.request.AnamneseRequestDTO;
import br.ueg.personalsystem.dto.response.AnamneseResponseDTO;
import br.ueg.personalsystem.entities.Anamnese;
import br.ueg.personalsystem.mapper.AnamneseMapper;
import br.ueg.personalsystem.service.impl.AnamneseService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.version}/anmnese")
public class AnamneseController extends AbstractCrudController<AnamneseRequestDTO, AnamneseResponseDTO, AnamneseListDTO, Anamnese, AnamneseService, AnamneseMapper, Long>
        implements IAnamneseController {
}
