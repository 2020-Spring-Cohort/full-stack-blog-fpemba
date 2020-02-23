package org.wcci.blog.storages;

import org.springframework.stereotype.Service;
import org.wcci.blog.models.Tag;
import org.wcci.blog.storages.Repositories.TagRepository;

import java.util.Collection;

@Service
public class TagStorageJpaImpl implements TagStorage {

    private TagRepository repository;

    public TagStorageJpaImpl(TagRepository repository) {
        this.repository = repository;
    }

    @Override
    public Collection<Tag> getAll() {
        return (Collection<Tag>) repository.findAll();
    }

    @Override
    public void store(Tag tag) {

        repository.save(tag);

    }

    @Override
    public Tag findTagByName(String name) {
        return repository.findByName(name).get();
    }
}
