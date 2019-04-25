package com.clientmanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clientmanager.model.Job;

public interface JobDAO extends JpaRepository<Job, Integer> {

}
