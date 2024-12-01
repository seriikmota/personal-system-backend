package br.ueg.personalsystem.repository;

import br.ueg.personalsystem.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
