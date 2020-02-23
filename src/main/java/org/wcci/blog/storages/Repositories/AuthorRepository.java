package org.wcci.blog.storages.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.wcci.blog.models.Author;

import java.util.Optional;

public interface AuthorRepository extends CrudRepository<Author, Long> {

    Optional<Author> findByName(String name);


}
