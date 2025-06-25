package ch.axa.punchclock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ch.axa.punchclock.models.Category;
import ch.axa.punchclock.repositories.CategoryRepository;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/category")
public class APICategoryController {
    
    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Category create(@RequestBody Category category){
        return categoryRepository.save(category);
    }

   @GetMapping
  public Iterable<Category> index() {
    return categoryRepository.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Category> read(@PathVariable Long id) {
    return ResponseEntity.of(categoryRepository.findById(id));
  }

  @PutMapping("/{id}")
  public Category update(@PathVariable Long id, @RequestBody @Valid Category person) {
    person.setId(id);
    return categoryRepository.save(person);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Category> delete(@PathVariable Long id) {
    var person = categoryRepository.findById(id);
    if(person.isPresent()) {
      categoryRepository.delete(person.get());
      return new ResponseEntity<>(HttpStatus.OK);
    }

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}

