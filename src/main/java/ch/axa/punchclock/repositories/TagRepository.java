package ch.axa.punchclock.repositories;

import org.springframework.data.repository.CrudRepository;

import ch.axa.punchclock.models.Tag;

public interface TagRepository extends CrudRepository<Tag,Long> {
    
}