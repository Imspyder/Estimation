package com.vehicle.schedular.repository;


import com.vehicle.schedular.model.ByteArrayEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ByteArrayRepository extends MongoRepository<ByteArrayEntity, String> {
	ByteArrayEntity findByFileName(String fileName);
	List<ByteArrayEntity> findByStatusNotIn(String status);
}
