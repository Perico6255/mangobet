package com.perico.apimongo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.perico.apimongo.models.User;

public interface UserRepository extends JpaRepository<User, Integer>{
    public User findByUsername(String username);
}
