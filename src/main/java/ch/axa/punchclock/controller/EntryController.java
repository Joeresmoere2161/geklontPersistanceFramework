package ch.axa.punchclock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ch.axa.punchclock.models.Entry;
import ch.axa.punchclock.repositories.EntryRepository;
import jakarta.validation.Valid;


@Controller
public class EntryController {

    @Autowired
    private EntryRepository entryRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("entries", entryRepository.findAll());
        return "index";
    }

    @GetMapping("/add")
    public String add(Entry entry) {
        return "add";
    }

    @PostMapping("/create")
    public String create(@Valid Entry entry, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add";
        }

        entryRepository.save(entry);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model) {
        Entry entry = entryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid entry Id:" + id));
        model.addAttribute("entry", entry);

        return "edit";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") long id, @Valid Entry entry, BindingResult result) {
        if (result.hasErrors()) {
            entry.setId(id);
            return "edit";
        }

        entryRepository.save(entry);

        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id, Model model) {
        Entry entry = entryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid entry Id:" + id));
        entryRepository.delete(entry);

        return "redirect:/";
    }
}