package org.wcci.blog.storages.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.wcci.blog.models.Category;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    Optional<Category> findByName(String name);
}
