package com.bridgelabz.fundoonote.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "notes") // 👈 IMPORTANT
@Getter
@Setter
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id") // 👈 IMPORTANT
    private User user;
}