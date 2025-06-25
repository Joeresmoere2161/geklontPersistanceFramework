package ch.axa.punchclock.repositories;

import org.springframework.data.repository.CrudRepository;

import ch.axa.punchclock.models.Category;

public interface CategoryRepository extends CrudRepository<Category,Long> {
    
}