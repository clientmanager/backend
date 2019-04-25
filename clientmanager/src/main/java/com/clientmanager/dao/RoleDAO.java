package com.clientmanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clientmanager.model.Role;

public interface RoleDAO extends JpaRepository<Role, Integer> {

}
