package org.wcci.blog.storages;


import org.wcci.blog.models.Tag;

import java.util.Collection;

public interface TagStorage {

    Collection<Tag> getAll();

    void store(Tag tag);

    Tag findTagByName(String name);
}
