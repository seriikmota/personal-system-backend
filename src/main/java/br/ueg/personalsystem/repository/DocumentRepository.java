package br.ueg.personalsystem.repository;

import br.ueg.personalsystem.entities.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    boolean existsDocumentByHash(String hash);
    boolean existsDocumentByName(String name);
    Document findFirstByHash(String hash);
    Document findFirstByName(String filename);
}
