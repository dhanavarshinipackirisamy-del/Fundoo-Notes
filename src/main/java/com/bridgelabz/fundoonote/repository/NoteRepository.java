package com.bridgelabz.fundoonote.repository;

import com.bridgelabz.fundoonote.entity.Note;
import com.bridgelabz.fundoonote.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findByUser(User user);
}