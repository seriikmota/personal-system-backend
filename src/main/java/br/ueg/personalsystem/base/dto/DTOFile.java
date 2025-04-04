package br.ueg.personalsystem.base.dto;

import java.util.List;

public interface DTOFile {
    List<FileDTO> getFiles();
    void setFiles(List<FileDTO> files);
}
