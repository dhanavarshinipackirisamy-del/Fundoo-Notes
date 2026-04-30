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

    // ✅ CREATE NOTE (NO 500)
    @PostMapping
    public Note create(@RequestBody Note note, Authentication auth) {

        if (auth == null) {
            throw new RuntimeException("Unauthorized: No authentication");
        }

        String email = auth.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        note.setUser(user);

        return noteRepository.save(note);
    }

    // ✅ GET NOTES (UC9)
    @GetMapping
    public List<Note> get(Authentication auth) {

        if (auth == null) {
            throw new RuntimeException("Unauthorized: No authentication");
        }

        String email = auth.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        return noteRepository.findByUser(user);
    }

    // ✅ UPDATE NOTE
    @PutMapping("/{id}")
    public Note update(@PathVariable Long id,
                       @RequestBody Note updatedNote,
                       Authentication auth) {

        if (auth == null) {
            throw new RuntimeException("Unauthorized");
        }

        String email = auth.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found"));

        if (!note.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized access to note");
        }

        note.setTitle(updatedNote.getTitle());
        note.setContent(updatedNote.getContent());

        return noteRepository.save(note);
    }

    // ✅ DELETE NOTE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id, Authentication auth) {

        if (auth == null) {
            throw new RuntimeException("Unauthorized");
        }

        String email = auth.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found"));

        if (!note.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized access to note");
        }

        noteRepository.delete(note);

        return "Note deleted successfully";
    }
}