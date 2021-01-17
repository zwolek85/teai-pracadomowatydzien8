package pl.zwolennik.notebook.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.zwolennik.notebook.entities.Note;
import pl.zwolennik.notebook.entities.Notebook;
import pl.zwolennik.notebook.services.NotebookService;

import java.util.List;

@Controller
@RequestMapping({"api"})
class NotebookController {

    private NotebookService notebookService;

    @Autowired
    public NotebookController(NotebookService notebookService) {
        this.notebookService = notebookService;
    }

    @GetMapping("/notebooks")
    public String getNotebooks(Model model) {
        List<Notebook> notebooks = notebookService.getAllNotebooks();
        model.addAttribute("notebooks", notebooks);
        return "notebooks";
    }

    @GetMapping("/addNotebook")
    public String getAddNotebookView(Model model) {
        model.addAttribute("notebook", new Notebook());
        return "addNotebook";
    }

    @PostMapping("/addNotebook")
    public String addNotebook(@ModelAttribute Notebook notebook) {
        notebookService.saveNotebook(notebook);
        return "redirect:/api/notebooks";
    }

    @GetMapping("/notebook/{id}/addNote")
    public String addNoteView(@PathVariable long id, Model model) {
        model.addAttribute("note", new Note());
        model.addAttribute("notebook", notebookService.getNotebookById(id));
        return "addNote";
    }

    @PostMapping("/notebook/{id}/addNote")
    public String addNote(@PathVariable long id, @ModelAttribute Note note) {
        notebookService.addNoteToNotebook(id, note);
        return "redirect:/api/notebooks";
    }

    @GetMapping("/notebook/{id}/getNotes")
    public String getNotes(@PathVariable long id, Model model) {
        Notebook notebook = notebookService.getNotebookById(id);
        model.addAttribute("notebook", notebook);
        model.addAttribute("notes", notebookService.getNotesByNotebook(notebook));
        return "notes";
    }

    @GetMapping("/note/{id}/editNote")
    public String editNoteView(@PathVariable("id") Long noteId, Model model) {
        Note noteById = notebookService.getNoteById(noteId);
        model.addAttribute("notebook", noteById.getNotebook());
        model.addAttribute("note", noteById);
        return "editNote";
    }

    @PostMapping("/note/{id}/editNote")
    public String editNote(@PathVariable("id") Long noteId, @ModelAttribute Note editedNote) {
        Note updatedNote = notebookService.editNote(noteId, editedNote);
        return "redirect:/api/notebook/" + updatedNote.getNotebook().getId() + "/getNotes";
    }

}
