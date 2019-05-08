package ru.karelin.tmwebspring.repository;

import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.karelin.tmwebspring.entity.User;

import javax.persistence.QueryHint;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    User findByLogin(String login);

    @QueryHints(@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_CACHEABLE, value = "true"))
    User findOneById(String id);
}
