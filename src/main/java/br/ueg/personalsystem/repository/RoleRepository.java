package br.ueg.personalsystem.repository;

import br.ueg.personalsystem.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
