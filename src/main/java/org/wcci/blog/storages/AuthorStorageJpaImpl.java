package org.wcci.blog.storages;

import org.springframework.stereotype.Service;
import org.wcci.blog.models.Author;
import org.wcci.blog.storages.Repositories.AuthorRepository;

import java.util.Collection;

@Service
public class AuthorStorageJpaImpl implements AuthorStorage {

    AuthorRepository repository;

    public AuthorStorageJpaImpl(AuthorRepository repository) {
        this.repository = repository;
    }

    @Override
    public Collection<Author> getAll() {
        return (Collection<Author>) repository.findAll();
    }

    @Override
    public void store(Author author) {

        repository.save(author);

    }

    @Override
    public Author findAuthorByName(String name) {
        return repository.findByName(name).get();
    }
}
