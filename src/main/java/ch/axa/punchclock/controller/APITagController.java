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

import ch.axa.punchclock.models.Tag;
import ch.axa.punchclock.repositories.TagRepository;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/tag")
public class APITagController {
    
    @Autowired
    private TagRepository tagRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Tag create(@RequestBody Tag tag){
        return tagRepository.save(tag);
    }

   @GetMapping
  public Iterable<Tag> index() {
    return tagRepository.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Tag> read(@PathVariable Long id) {
    return ResponseEntity.of(tagRepository.findById(id));
  }

  @PutMapping("/{id}")
  public Tag update(@PathVariable Long id, @RequestBody @Valid Tag person) {
    person.setId(id);
    return tagRepository.save(person);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Tag> delete(@PathVariable Long id) {
    var person = tagRepository.findById(id);
    if(person.isPresent()) {
      tagRepository.delete(person.get());
      return new ResponseEntity<>(HttpStatus.OK);
    }

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
