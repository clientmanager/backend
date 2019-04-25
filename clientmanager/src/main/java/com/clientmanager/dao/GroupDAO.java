package com.clientmanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clientmanager.model.Group;

public interface GroupDAO extends JpaRepository<Group, Integer> {

}
