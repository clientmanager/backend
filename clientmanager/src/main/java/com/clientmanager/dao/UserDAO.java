package com.clientmanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clientmanager.model.User;

public interface UserDAO extends JpaRepository<User, Integer> {

}
