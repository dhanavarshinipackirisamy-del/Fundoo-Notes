package com.bridgelabz.fundoonote.repository;

import com.bridgelabz.fundoonote.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {

    // 🔥 IMPORTANT (use this everywhere)
    List<Note> findByUserId(Long userId);
}