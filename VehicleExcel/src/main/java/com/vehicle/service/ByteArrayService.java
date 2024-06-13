package com.vehicle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vehicle.model.ByteArrayEntity;
import com.vehicle.repository.ByteArrayRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ByteArrayService {

	@Autowired
	private ByteArrayRepository byteArrayRepository;

	public void saveByteArray(String fileName, byte[] byteArrayData) {
		ByteArrayEntity byteArrayEntity = new ByteArrayEntity();
		byteArrayEntity.setFileName(fileName);
		byteArrayEntity.setByteArrayData(byteArrayData);
		byteArrayEntity.setStatus("incomplete");
		byteArrayEntity.setCreatedUser("Sachin");

		byteArrayRepository.save(byteArrayEntity);
	}

	public List<ByteArrayEntity> getFileDetails(){

		return byteArrayRepository.findAll();
	}
}
