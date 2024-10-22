package org.nicolas.optimaltech.repository;

import org.springframework.data.repository.CrudRepository;
import org.nicolas.optimaltech.entity.Role;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Optional<Role> findByName(String roleAdmin);
}
