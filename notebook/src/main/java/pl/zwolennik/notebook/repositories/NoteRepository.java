package pl.zwolennik.notebook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zwolennik.notebook.entities.Note;
import pl.zwolennik.notebook.entities.Notebook;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findAllByNotebook(Notebook notebook);
}
