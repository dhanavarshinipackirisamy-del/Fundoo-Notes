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

    // CREATE NOTE
    @PostMapping
    public Note create(@RequestBody Note note,
                       Authentication auth) {

        String email = auth.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        note.setUser(user);

        return noteRepository.save(note);
    }

    @GetMapping
    public List<Note> get(Authentication auth) {

        String email = auth.getName(); // ✅ works now

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return noteRepository.findByUser(user);
    }
    @PutMapping("/{id}")
    public Note update(@PathVariable Long id,
                       @RequestBody Note updatedNote,
                       Authentication auth) {

        String email = auth.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found"));

        // 🔐 SECURITY CHECK
        if (!note.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }

        note.setTitle(updatedNote.getTitle());
        note.setContent(updatedNote.getContent());

        return noteRepository.save(note);
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id,
                         Authentication auth) {

        String email = auth.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found"));

        // 🔐 SECURITY CHECK
        if (!note.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }

        noteRepository.delete(note);

        return "Note deleted successfully";
    }
}