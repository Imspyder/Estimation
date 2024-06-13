package com.vehicle.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.vehicle.model.ByteArrayEntity;



public interface ByteArrayRepository extends MongoRepository<ByteArrayEntity, String> {
}
