package ch.axa.punchclock.models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long id;

    @NotBlank(message = "Der Name darf nicht leer sein.")
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank(message = "Die Beschreibung darf nicht leer sein.")
    @Column(name="description", nullable = false)
    private String description;

    @ManyToMany
    @JoinTable(
      name = "tag_entries",
      joinColumns = @JoinColumn(name = "tag_id"),
      inverseJoinColumns = @JoinColumn(name = "entry_id"))
      private Set<Entry> entries = new HashSet<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
