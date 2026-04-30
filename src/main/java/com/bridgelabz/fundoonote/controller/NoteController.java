package com.bridgelabz.fundoonote.controller;

import com.bridgelabz.fundoonote.entity.Note;
import com.bridgelabz.fundoonote.entity.User;
import com.bridgelabz.fundoonote.repository.NoteRepository;
import com.bridgelabz.fundoonote.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    // ✅ CREATE
    @PostMapping
    public Note create(@RequestBody Note note, Authentication auth) {
        String email = auth.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        note.setUser(user);
        return noteRepository.save(note);
    }

    // ✅ GET
    @GetMapping
    public List<Note> get(Authentication auth) {
        String email = auth.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return noteRepository.findByUser(user);
    }

    // ✅ UPDATE
    @PutMapping("/{id}")
    public Note update(@PathVariable Long id,
                       @RequestBody Note updatedNote,
                       Authentication auth) {

        String email = auth.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found"));

        if (!note.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }

        note.setTitle(updatedNote.getTitle());
        note.setContent(updatedNote.getContent());

        return noteRepository.save(note);
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id, Authentication auth) {

        String email = auth.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found"));

        if (!note.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }

        noteRepository.delete(note);
        return "Note deleted successfully";
    }

    // 🔥 UC12 — TOGGLE PIN
    @PutMapping("/{id}/pin")
    public Note togglePin(@PathVariable Long id, Authentication auth) {

        User user = userRepository.findByEmail(auth.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found"));

        if (!note.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }

        note.setPinned(!note.isPinned());

        return noteRepository.save(note);
    }

    // 🔥 UC12 — TOGGLE ARCHIVE
    @PutMapping("/{id}/archive")
    public Note toggleArchive(@PathVariable Long id, Authentication auth) {

        User user = userRepository.findByEmail(auth.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found"));

        if (!note.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }

        note.setArchived(!note.isArchived());

        return noteRepository.save(note);
    }

    // 🔥 UC12 — TOGGLE TRASH
    @PutMapping("/{id}/trash")
    public Note toggleTrash(@PathVariable Long id, Authentication auth) {

        User user = userRepository.findByEmail(auth.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found"));

        if (!note.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }

        note.setTrashed(!note.isTrashed());

        return noteRepository.save(note);
    }
}