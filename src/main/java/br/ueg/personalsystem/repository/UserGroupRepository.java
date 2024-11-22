package br.ueg.personalsystem.repository;

import br.ueg.personalsystem.entities.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {
}
