package ch.axa.punchclock.services;

import java.util.List;
import org.springframework.stereotype.Service;

import ch.axa.punchclock.models.Entry;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class EntryService {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void createEntry(Entry entry){
        this.em.persist(entry);
    }
    public Entry readEntry(Long entry_id){
        return this.em.find(Entry.class, entry_id);
    }
    public List<Entry> index(){
        return this.em.createQuery("SELECT e FROM Entry e",Entry.class).getResultList();
    }
    @Transactional
    public void updateEntry(Entry entry){
        this.em.merge(entry);
    }
    @Transactional
    public void deleteEntry(Entry entry){
        Entry managedEntry = em.contains(entry) ? entry : em.merge(entry);
        em.remove(managedEntry);
    }
}
