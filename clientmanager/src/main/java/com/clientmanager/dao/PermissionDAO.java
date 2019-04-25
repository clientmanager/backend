package com.clientmanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clientmanager.model.Permission;

public interface PermissionDAO extends JpaRepository<Permission, Integer> {

}
