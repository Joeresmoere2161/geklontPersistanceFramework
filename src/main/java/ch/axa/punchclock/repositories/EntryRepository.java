package ch.axa.punchclock.repositories;

import org.springframework.data.repository.CrudRepository;

import ch.axa.punchclock.models.Entry;

public interface EntryRepository extends CrudRepository<Entry,Long> {
    
}
