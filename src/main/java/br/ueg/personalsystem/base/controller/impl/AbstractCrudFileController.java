package br.ueg.personalsystem.base.controller.impl;

import br.ueg.personalsystem.base.controller.IAbstractCrudFileController;
import br.ueg.personalsystem.base.domain.GenericModel;
import br.ueg.personalsystem.base.dto.DTOFile;
import br.ueg.personalsystem.base.dto.FileDTO;
import br.ueg.personalsystem.base.mapper.GenericMapper;
import br.ueg.personalsystem.base.service.IAbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AbstractCrudFileController<DTORequest extends DTOFile, DTOResponse, DTOList, MODEL extends GenericModel<TYPE_PK>, SERVICE
        extends IAbstractService<DTORequest, MODEL, TYPE_PK>, MAPPER extends GenericMapper<DTORequest, DTOResponse, DTOList, MODEL, TYPE_PK>, TYPE_PK>
        implements IAbstractCrudFileController<DTORequest, DTOResponse, DTOList, TYPE_PK> {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    protected SERVICE service;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    protected MAPPER mapper;

    @PostMapping
    @Transactional
    @PreAuthorize(value = "hasRole(#root.this.getRoleName('CREATE'))")
    public ResponseEntity<DTOResponse> create(@RequestPart DTORequest dto,
                                              @RequestPart List<MultipartFile> files) throws IOException {
        List<FileDTO> listImageDTO = new ArrayList<>();
        for (MultipartFile image : files) {
            FileDTO imageDTO = FileDTO.builder()
                    .file(image.getBytes())
                    .build();
            listImageDTO.add(imageDTO);
        }
        dto.setFiles(listImageDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDTO(service.createFromDTO(dto)));

    }

    @PutMapping(path = "/{id}")
    @Transactional
    @PreAuthorize(value = "hasRole(#root.this.getRoleName('UPDATE'))")
    public ResponseEntity<DTOResponse> update(@PathVariable TYPE_PK id, @RequestPart DTORequest dto,
                                              @RequestPart List<MultipartFile> files) throws IOException {

        List<FileDTO> listImageDTO = new ArrayList<>();
        for (MultipartFile image : files) {
            FileDTO imageDTO = FileDTO.builder()
                    .file(image.getBytes())
                    .build();
            listImageDTO.add(imageDTO);
        }
        dto.setFiles(listImageDTO);

        DTOResponse modelSaved = mapper.toDTO(service.updateFromDTO(id, dto));
        return ResponseEntity.ok(modelSaved);
    }

    @DeleteMapping(path = "/{id}")
    @Transactional
    @PreAuthorize(value = "hasRole(#root.this.getRoleName('DELETE'))")
    public ResponseEntity<DTOResponse> delete(@PathVariable TYPE_PK id){
        DTOResponse deleteDTO = mapper.toDTO(service.deleteById(id));
        return ResponseEntity.ok(deleteDTO);
    }

    @GetMapping
    @PreAuthorize("hasRole(#root.this.getRoleName('LISTALL'))")
    public ResponseEntity<Page<DTOList>> listAll(Pageable pageable){
        Page<DTOList> listDTO = service.listAll(pageable).map(obj -> mapper.toDTOList(obj));
        return ResponseEntity.ok(listDTO);
    }

    @GetMapping(path = "/{id}")
    @PreAuthorize(value = "hasRole(#root.this.getRoleName('READ'))")
    public ResponseEntity<DTOResponse> getById(@PathVariable TYPE_PK id){
        DTOResponse dtoResult = mapper.toDTO(service.getById(id));
        return ResponseEntity.ok(dtoResult);
    }

    public String getRoleName(String action){
        return "ROLE_".concat(this.service.getEntityType().getSimpleName().toUpperCase().concat("_"+action.toUpperCase()));
    }
}
