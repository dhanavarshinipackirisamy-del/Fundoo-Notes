package com.bridgelabz.fundoonote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bridgelabz.fundoonote.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}