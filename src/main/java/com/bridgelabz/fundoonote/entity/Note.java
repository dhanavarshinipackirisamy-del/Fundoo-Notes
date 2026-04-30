package com.bridgelabz.fundoonote.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "notes")
@Getter
@Setter
@JsonIgnoreProperties({"user"})  // 🔥 IMPORTANT FIX
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}