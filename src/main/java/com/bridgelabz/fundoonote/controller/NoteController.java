package com.bridgelabz.fundoonote.controller;

import com.bridgelabz.fundoonote.entity.Note;
import com.bridgelabz.fundoonote.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    // 🔥 CREATE NOTE
    @PostMapping("/{userId}")
    public Note createNote(@RequestBody Note note,
                           @PathVariable Long userId) {
        return noteService.createNote(note, userId);
    }

    // 🔥 GET ALL NOTES
    @GetMapping("/{userId}")
    public List<Note> getNotes(@PathVariable Long userId) {
        return noteService.getAllNotes(userId);
    }

    // 🔥 UPDATE
    @PutMapping("/{id}")
    public Note updateNote(@PathVariable Long id,
                           @RequestBody Note note) {
        return noteService.updateNote(id, note);
    }

    // 🔥 DELETE
    @DeleteMapping("/{id}")
    public String deleteNote(@PathVariable Long id) {
        noteService.deleteNote(id);
        return "Deleted successfully";
    }
}