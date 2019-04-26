package com.clientmanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clientmanager.model.Team;

public interface TeamDAO extends JpaRepository<Team, Integer> {

}
