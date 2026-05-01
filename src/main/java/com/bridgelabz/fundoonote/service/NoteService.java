package com.bridgelabz.fundoonote.service;

import com.bridgelabz.fundoonote.entity.Note;

import java.util.List;

public interface NoteService {

    Note createNote(Note note, Long userId);

    List<Note> getAllNotes(Long userId);

    Note updateNote(Long id, Note note);

    void deleteNote(Long id);
}