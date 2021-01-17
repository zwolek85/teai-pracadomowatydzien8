package pl.zwolennik.notebook.services;

import pl.zwolennik.notebook.entities.Note;
import pl.zwolennik.notebook.entities.Notebook;

import java.util.List;

public interface NotebookService {

    List<Notebook> getAllNotebooks();

    void saveNotebook(Notebook notebook);

    Notebook getNotebookById(Long id);

    void addNoteToNotebook(Long notebookId, Note note);

    List<Note> getNotesByNotebook(Notebook notebook);

    Note getNoteById(Long noteId);

    Note editNote(Long noteId, Note editedNote);
}
