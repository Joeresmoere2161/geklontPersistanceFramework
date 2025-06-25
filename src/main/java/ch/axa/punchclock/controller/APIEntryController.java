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

import ch.axa.punchclock.models.Entry;
import ch.axa.punchclock.repositories.EntryRepository;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/entry")
public class APIEntryController {
    
    @Autowired
    private EntryRepository entryRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entry create(@RequestBody Entry entry){
        return entryRepository.save(entry);
    }

   @GetMapping
  public Iterable<Entry> index() {
    return entryRepository.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Entry> read(@PathVariable Long id) {
    return ResponseEntity.of(entryRepository.findById(id));
  }

  @PutMapping("/{id}")
  public Entry update(@PathVariable Long id, @RequestBody @Valid Entry person) {
    person.setId(id);
    return entryRepository.save(person);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Entry> delete(@PathVariable Long id) {
    var person = entryRepository.findById(id);
    if(person.isPresent()) {
      entryRepository.delete(person.get());
      return new ResponseEntity<>(HttpStatus.OK);
    }

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
