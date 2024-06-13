package com.vehicle.schedular.repository;

import com.vehicle.schedular.model.FileProcessingLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileProcessingRepository extends JpaRepository<FileProcessingLog,String> {
}
