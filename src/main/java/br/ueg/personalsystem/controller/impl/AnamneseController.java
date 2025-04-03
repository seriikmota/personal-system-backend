package br.ueg.personalsystem.controller.impl;

import br.ueg.personalsystem.base.controller.impl.AbstractCrudController;
import br.ueg.personalsystem.controller.IAnamneseController;
import br.ueg.personalsystem.dto.list.AnamneseListDTO;
import br.ueg.personalsystem.dto.request.AnamneseRequestDTO;
import br.ueg.personalsystem.dto.response.AnamneseResponseDTO;
import br.ueg.personalsystem.entities.Anamnese;
import br.ueg.personalsystem.mapper.AnamneseMapper;
import br.ueg.personalsystem.service.impl.AnamneseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("${api.version}/anmnese")
public class AnamneseController extends AbstractCrudController<AnamneseRequestDTO, AnamneseResponseDTO, AnamneseListDTO, Anamnese, AnamneseService, AnamneseMapper, Long>
        implements IAnamneseController {

    @GetMapping(path = "/anamnese/search")
    @PreAuthorize("hasRole(#root.this.getRoleName('LISTALL'))")
    public ResponseEntity<Page<AnamneseListDTO>> searchAnamnese(@RequestParam(required = false) String patientName, @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate, Pageable pageable) {
        Page<AnamneseListDTO> anamneses = service.search(patientName, startDate, endDate, pageable).map(obj->mapper.toDTOList(obj));
        return ResponseEntity.ok(anamneses);
    }
}
