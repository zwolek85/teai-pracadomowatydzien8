package pl.zwolennik.notebook.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.zwolennik.notebook.entities.Note;
import pl.zwolennik.notebook.entities.Notebook;
import pl.zwolennik.notebook.repositories.NoteRepository;
import pl.zwolennik.notebook.repositories.NotebookRepository;
import pl.zwolennik.notebook.services.NotebookService;

import java.util.List;

@Service
class NotebookServiceImpl implements NotebookService {

    private NotebookRepository notebookRepository;
    private NoteRepository noteRepository;

    @Autowired
    public NotebookServiceImpl(NotebookRepository notebookRepository, NoteRepository noteRepository) {
        this.notebookRepository = notebookRepository;
        this.noteRepository = noteRepository;
    }

    @Override
    public List<Notebook> getAllNotebooks() {
        return notebookRepository.findAll(Sort.by("title"));
    }

    @Override
    public void saveNotebook(Notebook notebook) {
        notebookRepository.save(notebook);
    }

    @Override
    public Notebook getNotebookById(Long id) {
        return notebookRepository.getOne(id);
    }

    @Override
    public void addNoteToNotebook(Long notebookId, Note note) {
        Notebook notebook = notebookRepository.getOne(notebookId);
        note.setNotebook(notebook);
        noteRepository.save(note);
    }

    @Override
    public List<Note> getNotesByNotebook(Notebook notebook) {
        return noteRepository.findAllByNotebook(notebook);
    }

    @Override
    public Note getNoteById(Long noteId) {
        return noteRepository.getOne(noteId);
    }

    @Override
    public Note editNote(Long noteId, Note editedNote) {
        Note note = noteRepository.getOne(noteId);
        note.setTitle(editedNote.getTitle());
        note.setContent(editedNote.getContent());
        return noteRepository.save(note);
    }
}
