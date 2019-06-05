package ru.karelin.tmwebspring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.karelin.tmwebspring.entity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, String> {
    Role findByName(String name);
}
