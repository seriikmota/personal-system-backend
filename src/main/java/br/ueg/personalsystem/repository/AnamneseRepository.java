package br.ueg.personalsystem.repository;

import br.ueg.personalsystem.entities.Anamnese;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnamneseRepository extends JpaRepository<Anamnese, Long> {
}
