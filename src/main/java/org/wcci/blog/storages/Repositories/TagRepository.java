package org.wcci.blog.storages.Repositories;


import org.springframework.data.repository.CrudRepository;
import org.wcci.blog.models.Tag;

import java.util.Optional;

public interface TagRepository extends CrudRepository<Tag, Long> {

    Optional<Tag> findByName(String name);
}
