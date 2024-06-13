package com.vehicle.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vehicle.main.entity.Role;
@Repository

public interface RoleRepository extends JpaRepository<Role, Long> {

}
