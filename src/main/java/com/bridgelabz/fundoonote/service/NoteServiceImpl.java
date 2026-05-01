package com.bridgelabz.fundoonote.service;

import com.bridgelabz.fundoonote.entity.Note;
import com.bridgelabz.fundoonote.entity.User;
import com.bridgelabz.fundoonote.repository.NoteRepository;
import com.bridgelabz.fundoonote.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    // 🔥 CREATE NOTE
    @Override
    @CacheEvict(value = "notes", key = "#userId")
    public Note createNote(Note note, Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        note.setUser(user);

        return noteRepository.save(note);
    }

    // 🔥 GET NOTES (CACHED)
    @Override
    @Cacheable(value = "notes", key = "#userId")
    public List<Note> getAllNotes(Long userId) {

        System.out.println("🔥 FETCHING FROM DB...");

        return noteRepository.findByUserId(userId);
    }

    // 🔥 UPDATE NOTE
    @Override
    @CacheEvict(value = "notes", allEntries = true)
    public Note updateNote(Long id, Note updatedNote) {

        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found"));

        note.setTitle(updatedNote.getTitle());
        note.setContent(updatedNote.getContent());

        return noteRepository.save(note);
    }

    // 🔥 DELETE NOTE
    @Override
    @CacheEvict(value = "notes", allEntries = true)
    public void deleteNote(Long id) {

        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found"));

        noteRepository.delete(note);
    }
}