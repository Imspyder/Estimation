package com.vehicle.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vehicle.main.entity.VehicleErrorLog;

/**
 * Repository to persist vehicle_erroe_log in database
 * @author PA00961610
 *
 */
@Repository
public interface VehicleErrorLogRepository extends JpaRepository<VehicleErrorLog, String>{

}
